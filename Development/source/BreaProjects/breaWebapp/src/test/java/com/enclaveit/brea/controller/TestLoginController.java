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

import com.enclaveit.brea.controller.LoginController;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TestLoginController {

    @Autowired
    private LoginController controller;

    private MockMvc mockMvc;

    private static final String PREFIX = "/WEB-INF/pages/";
    private static final String SUFFIX = ".jsp";

    private static final String LOGIN_VIEW_PATH = "/login";
    private static final String LOGIN_VIEW_NAME = "login";
    private static final String LOGIN_PAGE_FORWARDED_URL_EXPECT = "/WEB-INF/pages/login.jsp";

    @Before
    public void setUp() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup view resolver for spring test
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(PREFIX);
        viewResolver.setSuffix(SUFFIX);

        // Setup Spring test in standalone mode
        mockMvc = MockMvcBuilders.standaloneSetup(new LoginController())
                .setViewResolvers(viewResolver).build();
    }

    @Test
    public void testLoginPage() throws Exception {
        mockMvc.perform(get(LOGIN_VIEW_PATH)).andExpect(status().isOk())
                .andExpect(view().name(LOGIN_VIEW_NAME))
                .andExpect(forwardedUrl(LOGIN_PAGE_FORWARDED_URL_EXPECT));
    }
}
