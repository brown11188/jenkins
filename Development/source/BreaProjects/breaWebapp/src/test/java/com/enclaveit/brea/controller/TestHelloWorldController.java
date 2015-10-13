package com.enclaveit.brea.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TestHelloWorldController {

    @Autowired
    private HelloWorldController controller;

    private MockMvc mockMvc;

    private static final String PREFIX = "/WEB-INF/pages/";
    private static final String SUFFIX = ".jsp";

    private static final String BOOTSTRAP_VIEW_PATH = "/hellobootstrap";
    private static final String BOOTSTAP_VIEW_NAME = "hellobootstrap";
    private static final String BOOTSTAP_VIEW_NAME_PAGE_FORWARDED_URL_EXPECT = "/WEB-INF/pages/hellobootstrap.jsp";

    private static final String THEME_VIEW_PATH = "/hellotheme";
    private static final String THEME_VIEW_NAME = "hellotheme";
    private static final String THEME_VIEW_NAME_PAGE_FORWARDED_URL_EXPECT = "/WEB-INF/pages/hellotheme.jsp";

    @Before
    public void setUp() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup view resolver for spring test
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(PREFIX);
        viewResolver.setSuffix(SUFFIX);

        // Setup Spring test in standalone mode
        mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController())
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void testSampleBootstrap() throws Exception {
        mockMvc.perform(get(BOOTSTRAP_VIEW_PATH)).andExpect(status().isOk())
                .andExpect(view().name(BOOTSTAP_VIEW_NAME))
                .andExpect(forwardedUrl(BOOTSTAP_VIEW_NAME_PAGE_FORWARDED_URL_EXPECT));
    }

    @Test
    public void testSampleTheme() throws Exception {
        mockMvc.perform(get(THEME_VIEW_PATH)).andExpect(status().isOk())
                .andExpect(view().name(THEME_VIEW_NAME))
                .andExpect(forwardedUrl(THEME_VIEW_NAME_PAGE_FORWARDED_URL_EXPECT));
    }
}
