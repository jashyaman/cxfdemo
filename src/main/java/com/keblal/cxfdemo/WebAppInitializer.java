package com.keblal.cxfdemo;

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/*
 * This is the 'class' equivalent of web.xml
 */

public class WebAppInitializer implements WebApplicationInitializer {

    
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addListener(new ContextLoaderListener(createWebAppContext()));
        addApacheCxfServlet(servletContext);
    }

    /*
    * This is the Apache CXF equivalent of Sprng MVC DispatcherServlet
    */
    private void addApacheCxfServlet(ServletContext servletContext) {
        CXFServlet cxfServlet = new CXFServlet();

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("CXFServlet", cxfServlet);
        appServlet.setLoadOnStartup(1);

        Set<String> mappingConflicts = appServlet.addMapping("/api/*");
    }

    /*
    * this the java class equivalent of dispatcher-servlet.xml file
    */
    private WebApplicationContext createWebAppContext() {
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(ApplnContext.class);
        return appContext;
    }

}
