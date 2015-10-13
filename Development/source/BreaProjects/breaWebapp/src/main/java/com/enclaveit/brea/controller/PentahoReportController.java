package com.enclaveit.brea.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.enclaveit.brea.common.Const;
import com.enclaveit.brea.service.report.pentaho.PentahoReportGenerator;
import com.enclaveit.brea.service.report.pentaho.ReportOutputType;

@Controller
public class PentahoReportController {

    @Autowired
    ServletContext context;
    @Autowired
    private Properties pentahoDsProperties;
    @Autowired
    private PentahoReportGenerator pentahoReportGenerator;
    private static final Logger LOG = LoggerFactory.getLogger(PentahoReportController.class);
    private Session session = null;
    SQLQuery query = null;

    private static final String YEAR_ID = "ORDERFACT.YEAR_ID, ";
    private static final String PRODUCTVENDOR = "PRODUCTS.PRODUCTVENDOR ";
    private static final String REPORT_MESSAGE = "Report generation failure";

    @Autowired
    private SessionFactory sessionFactory;
    List<Map<String, Object>> yearValue = null;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "display", method = RequestMethod.GET)
    public ModelAndView reportPage() {
        ModelAndView model = new ModelAndView();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        session = sessionFactory.openSession();
        session.beginTransaction();
        yearValue = (List<Map<String, Object>>) session.createSQLQuery("SELECT DISTINCT * FROM ORDERFACT").list();
        session.close();
        modelMap.put("year_value", yearValue);
        model.addAllObjects(modelMap);
        model.setViewName("report");
        return model;
    }

    @RequestMapping(value = "report/areaByGroupGetParams", produces = "application/pdf")
    @ResponseBody
    public void areaByGroupReportParams(OutputStream outputStream, HttpServletRequest request) {
        String[] yearId = request.getParameterValues("year_id");
        generateReportGetParams("areaByGroup", yearId[0], ReportOutputType.PDF, outputStream);
    }

    @RequestMapping(value = "report/{reportName}/{outputType}")
    @ResponseBody
    public void runReport(@PathVariable("reportName") String reportName, @PathVariable("outputType") String outputType,
            HttpServletRequest request, HttpServletResponse response, OutputStream outputStream) {
        final ReportOutputType reportOutputType;
        if (request.getParameter("outputType") != null) {
            reportOutputType = ReportOutputType.valueOf(request.getParameter("outputType"));
        } else {
            reportOutputType = ReportOutputType.valueOf(outputType);
        }
        // Sets report output type
        setContentType(reportName, reportOutputType, response);
        // Report parameters
        Map<String, Object> reportParams = getReportParams(reportName, request);
        // Generates report
        generateReport(reportName, reportParams, reportOutputType, request, outputStream);
    }

    private Map<String, Object> getReportParams(final String reportName, final HttpServletRequest request) {
        Map<String, Object> reportParams = null;
        if ("areaByGroup".equals(reportName)) {
            final String yearId = request.getParameter("yearId");
            reportParams = new HashMap<String, Object>();
            reportParams.put("YEAR", yearId);
        }
        return reportParams;
    }

    private void setContentType(String reportName, ReportOutputType reportOutputType, HttpServletResponse response) {
        if (ReportOutputType.EXCEL.equals(reportOutputType)) {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + reportName + ".xls");
        } else if (ReportOutputType.HTML.equals(reportOutputType)) {
            response.setContentType(MediaType.TEXT_HTML_VALUE);
        } else {
            response.setContentType("application/pdf");
        }
    }

    private String getReportFile(String reportName) {
        return "file:" + context.getRealPath("pentaho/" + reportName + ".prpt");
    }

    private String getReportQuery(String reportName) {
        return pentahoDsProperties.getProperty("pentaho.report." + reportName + ".reportQuery");
    }

    private String getPentahoTempDir() {
        return pentahoDsProperties.getProperty(Const.TEMP_DIR);
    }

    /**
     * Generates report
     *
     * @param reportName
     *            Name of report
     * @param reportParams
     *            The set of report parameters to be used by the report
     *            generation process, or <code>null</code> if no parameters are
     *            required.
     * @param outputType
     *            Report output type
     * @param request
     *            HttpServletRequest object
     * @param outputStream
     *            OutputStream object
     */
    private void generateReport(String reportName, Map<String, Object> reportParams, ReportOutputType outputType,
            HttpServletRequest request, OutputStream outputStream) {
        final String reportFile = getReportFile(reportName);
        final String reportQuery = getReportQuery(reportName);
        pentahoReportGenerator.setReportFile(reportFile);
        pentahoReportGenerator.setDataFactory(pentahoDsProperties.getProperty(Const.DRIVER),
                pentahoDsProperties.getProperty(Const.URL), pentahoDsProperties.getProperty(Const.USER),
                pentahoDsProperties.getProperty(Const.PWD), reportQuery);
        pentahoReportGenerator.setReportParameters(reportParams);
        try {
            if (ReportOutputType.HTML.equals(outputType)) {
                final String pentahoTempDir = getPentahoTempDir();
                File dataDirectory = new File(context.getRealPath(pentahoTempDir));
                if (!dataDirectory.exists()) {
                    dataDirectory.mkdirs();
                }

                String resourcePath = request.getContextPath() + "/" + pentahoTempDir;

                pentahoReportGenerator.generateHtmlReport(dataDirectory, resourcePath, outputStream);
            } else {
                pentahoReportGenerator.generateReport(outputType, outputStream);
            }
        } catch (IllegalArgumentException e) {
            LOG.error(REPORT_MESSAGE, e);
        } catch (ReportProcessingException e) {
            LOG.error(REPORT_MESSAGE, e);
        }
    }

    private void generateReportGetParams(String reportName, String yearId, ReportOutputType outputType,
            OutputStream outputStream) {
        final String reportFile = getReportFile(reportName);
        // Create SQL option
        final StringBuilder sqlOption = new StringBuilder().append("SELECT ")
                .append("sum(ORDERFACT.TOTALPRICE) AS SALES, ").append("sum(ORDERFACT.QUANTITYORDERED) AS VOLUME, ")
                .append("PRODUCTS.PRODUCTLINE, ").append(YEAR_ID).append(PRODUCTVENDOR).append("FROM PRODUCTS ")
                .append("INNER JOIN ORDERFACT ").append("ON PRODUCTS.PRODUCTCODE = ORDERFACT.PRODUCTCODE ")
                .append("WHERE ORDERFACT.YEAR_ID = ").append(yearId + " ").append("GROUP BY PRODUCTS.PRODUCTLINE, ")
                .append(YEAR_ID).append(PRODUCTVENDOR).append("ORDER BY ORDERFACT.YEAR_ID ASC, ")
                .append("PRODUCTS.PRODUCTLINE ASC, ").append("PRODUCTS.PRODUCTVENDOR ASC ");

        // Create SQL select All value (year_id)
        final StringBuilder sqlSelectAll = new StringBuilder().append("SELECT ")
                .append("sum(ORDERFACT.TOTALPRICE) AS SALES, ").append("sum(ORDERFACT.QUANTITYORDERED) AS VOLUME, ")
                .append("PRODUCTS.PRODUCTLINE, ").append(YEAR_ID).append(PRODUCTVENDOR).append("FROM PRODUCTS ")
                .append("INNER JOIN ORDERFACT ").append("ON PRODUCTS.PRODUCTCODE = ORDERFACT.PRODUCTCODE ")
                .append("GROUP BY PRODUCTS.PRODUCTLINE, ").append(YEAR_ID).append(PRODUCTVENDOR)
                .append("ORDER BY ORDERFACT.YEAR_ID ASC, ").append("PRODUCTS.PRODUCTLINE ASC, ")
                .append("PRODUCTS.PRODUCTVENDOR ASC ");
        // Open Session
        session = getSessionFactory().openSession();
        if ("0".equals(yearId.toString())) {
            query = session.createSQLQuery(sqlSelectAll.toString());
        } else {
            query = session.createSQLQuery(sqlOption.toString());
        }
        // Report
        pentahoReportGenerator.setReportFile(reportFile);
        pentahoReportGenerator.setDataFactory(pentahoDsProperties.getProperty(Const.DRIVER),
                pentahoDsProperties.getProperty(Const.URL), pentahoDsProperties.getProperty(Const.USER),
                pentahoDsProperties.getProperty(Const.PWD), query.getQueryString());
        try {
            pentahoReportGenerator.generateReport(outputType, outputStream);
        } catch (IllegalArgumentException e) {
            LOG.error(REPORT_MESSAGE, e);
        } catch (ReportProcessingException e) {
            LOG.error(REPORT_MESSAGE, e);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
