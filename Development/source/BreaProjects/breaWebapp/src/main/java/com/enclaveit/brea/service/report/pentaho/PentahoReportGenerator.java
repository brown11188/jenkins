package com.enclaveit.brea.service.report.pentaho;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.pentaho.reporting.engine.classic.core.layout.output.AbstractReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.SQLReportDataFactory;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.base.PageableReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.pageable.pdf.PdfOutputProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.base.FlowReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.base.StreamReportProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.AllItemsHtmlPrinter;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.FileSystemURLRewriter;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.HtmlOutputProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.HtmlPrinter;
import org.pentaho.reporting.engine.classic.core.modules.output.table.html.StreamHtmlOutputProcessor;
import org.pentaho.reporting.engine.classic.core.modules.output.table.xls.FlowExcelOutputProcessor;
import org.pentaho.reporting.libraries.repository.ContentIOException;
import org.pentaho.reporting.libraries.repository.ContentLocation;
import org.pentaho.reporting.libraries.repository.DefaultNameGenerator;
import org.pentaho.reporting.libraries.repository.file.FileRepository;
import org.pentaho.reporting.libraries.repository.stream.StreamRepository;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PentahoReportGenerator {

    private static final String QUERY_NAME = "ReportQuery";
    private String reportFile;
    private Map<String, Object> reportParams;
    private SQLReportDataFactory dataFactory;

    private static final Logger LOG = LoggerFactory.getLogger(PentahoReportGenerator.class);

    /**
     * Performs the basic initialization required to generate a report
     */
    public PentahoReportGenerator() {
        // Initialize the reporting engine
        ClassicEngineBoot.getInstance().start();
    }

    /**
     * Sets the report file's full path
     * @param reportFileName The report file's full path
     */
    public void setReportFile(final String reportFile) {
        this.reportFile = reportFile;
    }

    /**
     * Gets the report file's full path
     * @return The report file's full path
     */
    private String getReportFile() {
        return this.reportFile;
    }

    /**
     * Returns the report definition used by this report generator. If this method returns <code>null</code>,
     * the report generation process will throw a <code>NullPointerException</code>.
     *
     * @return the report definition used by this report generator
     */
    private MasterReport getReportDefinition() {
        MasterReport report = null;
        try {
            final URL reportDefinitionURL = new URL(getReportFile());

            // Parse the report file
            final ResourceManager resourceManager = new ResourceManager();
            resourceManager.registerDefaults();
            final Resource directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
            report = (MasterReport) directly.getResource();
        } catch (ResourceException e) {
            LOG.error("Error parsing report definition", e);
        } catch (MalformedURLException e) {
            LOG.error("Error parsing report definition", e);
        }
        return report;
    }

    /**
     * Sets the data factory to be used by this report generator.
     *
     * @param driver driver class name
     * @param url database url
     * @param username database username
     * @param password database password
     * @param reportQuery report query
     */
    public void setDataFactory(final String driver, final String url, final String username, final String password,
            final String reportQuery) {
        final DriverConnectionProvider sampleDriverConnectionProvider = new DriverConnectionProvider();
        sampleDriverConnectionProvider.setDriver(driver);
        sampleDriverConnectionProvider.setUrl(url);
        sampleDriverConnectionProvider.setProperty("user", username);
        sampleDriverConnectionProvider.setProperty("password", password);

        this.dataFactory = new SQLReportDataFactory(sampleDriverConnectionProvider);
        this.dataFactory.setQuery(QUERY_NAME, reportQuery);
    }

    private DataFactory getDataFactory() {
        return this.dataFactory;
    }

    /**
     * Sets the report parameters to be used by the report generation process, or <code>null</code> if no
     * parameters are required.
     *
     * @param reportParams the set of report parameters to be used by the report generation process,
     *                     or <code>null</code> if no parameters are required.
     */
    public void setReportParameters(final Map<String, Object> reportParams) {
        this.reportParams = reportParams;
    }

    /**
     * Returns the set of parameters that will be passed to the report generation process. If there are no parameters
     * required for report generation, this method may return either an empty or a <code>null</code> <code>Map</code>
     *
     * @return the set of report parameters to be used by the report generation process, or <code>null</code> if no
     *         parameters are required.
     */
    private Map<String, Object> getReportParameters() {
        return this.reportParams;
    }

    /**
     * Generates the report in the specified <code>outputType</code> and writes it into the specified
     * <code>outputFile</code>.
     *
     * @param outputType the output type of the report (HTML, PDF, HTML)
     * @param outputFile the file into which the report will be written
     * @throws IllegalArgumentException  indicates the required parameters were not provided
     * @throws IOException               indicates an error opening the file for writing
     * @throws ReportProcessingException indicates an error generating the report
     */
    public void generateReport(final ReportOutputType outputType, File outputFile)
            throws ReportProcessingException {
        if (outputFile == null) {
            throw new IllegalArgumentException("The output file was not specified");
        }

        OutputStream outputStream = null;
        try {
            // Open the output stream
            outputStream = new BufferedOutputStream(new FileOutputStream(outputFile));

            // Generate the report to this output stream
            generateReport(outputType, outputStream);
        } catch (FileNotFoundException e) {
            LOG.error("Error opening output stream", e);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    LOG.error("Error closing output stream", e);
                }
            }
        }
    }

    private PageableReportProcessor getPdfReportProcessor(MasterReport report, OutputStream outputStream) {
        PageableReportProcessor reportProcessor = null;
        final PdfOutputProcessor outputProcessor =
                new PdfOutputProcessor(report.getConfiguration(),
                        outputStream, report.getResourceManager());
        try {
            reportProcessor = new PageableReportProcessor(report, outputProcessor);
        } catch (ReportProcessingException e) {
            LOG.error("Error creating PDF report processor", e);
        }
        return reportProcessor;
    }

    private FlowReportProcessor getExcelReportProcessor(MasterReport report, OutputStream outputStream) {
        FlowReportProcessor reportProcessor = null;
        final FlowExcelOutputProcessor target =
                new FlowExcelOutputProcessor(report.getConfiguration(),
                        outputStream, report.getResourceManager());
        try {
            reportProcessor = new FlowReportProcessor(report, target);
        } catch (ReportProcessingException e) {
            LOG.error("Error creating Excel report processor", e);
        }
        return reportProcessor;
    }

    private StreamReportProcessor getHtmlReportProcessor(MasterReport report, OutputStream outputStream) {
        StreamReportProcessor reportProcessor = null;
        final StreamRepository targetRepository = new StreamRepository(outputStream);
        final ContentLocation targetRoot = targetRepository.getRoot();
        final HtmlOutputProcessor outputProcessor =
                new StreamHtmlOutputProcessor(report.getConfiguration());
        final HtmlPrinter printer = new AllItemsHtmlPrinter(report.getResourceManager());
        printer.setContentWriter(targetRoot, new DefaultNameGenerator(targetRoot, "index", "html"));
        printer.setDataWriter(null, null);
        printer.setUrlRewriter(new FileSystemURLRewriter());
        outputProcessor.setPrinter(printer);
        try {
            reportProcessor = new StreamReportProcessor(report, outputProcessor);
        } catch (ReportProcessingException e) {
            LOG.error("Error creating Html report processor", e);
        }
        return reportProcessor;
    }

    /**
     * Generates the report in the specified <code>outputType</code> and writes it into the specified
     * <code>outputStream</code>.
     * <p/>
     * It is the responsibility of the caller to close the <code>outputStream</code>
     * after this method is executed.
     *
     * @param outputType   the output type of the report (HTML, PDF, HTML)
     * @param outputStream the stream into which the report will be written
     * @throws IllegalArgumentException  indicates the required parameters were not provided
     * @throws ReportProcessingException indicates an error generating the report
     */
    public void generateReport(final ReportOutputType outputType, OutputStream outputStream)
            throws ReportProcessingException {
        if (outputStream == null) {
            throw new IllegalArgumentException("The output stream was not specified");
        }

        // Get the report
        final MasterReport report = getReportDefinition();

        // Set the data factory for the report
        if (getDataFactory() != null) {
            report.setDataFactory(getDataFactory());
            report.setQuery(QUERY_NAME);
        }

        // Add any parameters to the report
        final Map<String, Object> reportParameters = getReportParameters();
        if (null != reportParameters) {
            for (String key : reportParameters.keySet()) {
                report.getParameterValues().put(key, reportParameters.get(key));
            }
        }

        // Prepare to generate the report
        AbstractReportProcessor reportProcessor = null;

        // Create the report processor for the specified output type
        switch (outputType) {
            case PDF:
                reportProcessor = getPdfReportProcessor(report, outputStream);
                break;
            case EXCEL:
                reportProcessor = getExcelReportProcessor(report, outputStream);
                break;
            case HTML:
                reportProcessor = getHtmlReportProcessor(report, outputStream);
                break;
            default:
                break;
        }

        try {
            // Generate the report
            reportProcessor.processReport();
        } finally {
            if (reportProcessor != null) {
                reportProcessor.close();
            }
        }
    }

    /**
     * Generates HTML report
     *
     * @param dataDirectory Full path to the directory where Pentaho saves temporary files generated during
     *                      report generation process
     * @param resourcePath Relative path to the directory containing report's images, stylesheets, etc.
     * @param outputStream OutputStream object
     * @throws ReportProcessingException Report Processing Exception
     */
    public void generateHtmlReport(File dataDirectory, String resourcePath,
            OutputStream outputStream) throws ReportProcessingException {
        // Get the report
        final MasterReport report = getReportDefinition();

        // Set the data factory for the report
        if (getDataFactory() != null) {
            report.setDataFactory(getDataFactory());
            report.setQuery(QUERY_NAME);
        }

        // Add any parameters to the report
        final Map<String, Object> reportParameters = getReportParameters();
        if (null != reportParameters) {
            for (String key : reportParameters.keySet()) {
                report.getParameterValues().put(key, reportParameters.get(key));
            }
        }

        try {
            final FileRepository dataRepository = new FileRepository(dataDirectory);
            ContentLocation dataLocation = dataRepository.getRoot();
            DefaultNameGenerator dataNameGenerator = new DefaultNameGenerator(dataLocation);
            HtmlURLRewriter rewriter = new HtmlURLRewriter(resourcePath + "/{0}", false);

            final StreamRepository targetRepository = new StreamRepository(null, outputStream, report.getName());
            final ContentLocation targetRoot = targetRepository.getRoot();

            final HtmlOutputProcessor outputProcessor =
                    new StreamHtmlOutputProcessor(report.getConfiguration());
            final HtmlPrinter printer = new AllItemsHtmlPrinter(report.getResourceManager());
            printer.setContentWriter(targetRoot, new DefaultNameGenerator(targetRoot, "index", "html"));
            printer.setDataWriter(dataLocation, dataNameGenerator);
            printer.setUrlRewriter(rewriter);
            outputProcessor.setPrinter(printer);

            final StreamReportProcessor sp = new StreamReportProcessor(report, outputProcessor);
            sp.processReport();
            sp.close();
        } catch (ContentIOException e) {
            LOG.error("Report generation failure", e);
        }
    }
}
