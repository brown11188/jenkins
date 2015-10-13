package com.enclaveit.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.text.IsEmptyString.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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

import com.enclaveit.controller.MainController;

@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
public class TestMainController {

    private static final String WELCOME_FORWARDED_URL = "/WEB-INF/pages/hello.jsp";
    private static final String LOGIN_FORWARDED_URL = "/WEB-INF/pages/login.jsp";
    
    @Autowired
    private MainController mainController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
                    viewResolver.setPrefix("/WEB-INF/pages/");
                    viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(new MainController())
                    .setViewResolvers(viewResolver).build();
    }

    @Test
    public void testFirstPageShow() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("hello"))
                .andExpect(forwardedUrl(WELCOME_FORWARDED_URL))
                .andExpect(model().attributeExists("title"))
                .andExpect(model().attributeExists("message"));
    }

    @Test
    public void testLoginPageShow() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(forwardedUrl(LOGIN_FORWARDED_URL));
    }
}
