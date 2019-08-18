package com.k10.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class DispatcherServlet extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {

        Class [] classes = new Class[] { SpringWebConfig.class };

        return classes;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

}






