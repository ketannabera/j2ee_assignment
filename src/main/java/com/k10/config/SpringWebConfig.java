package com.k10.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.k10")
@PropertySource("classpath:mysql.properties")
public class SpringWebConfig {

    @Autowired
    private Environment env;


    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

    @Bean
    public DataSource securityDataSource() {

        // create connection pool
        ComboPooledDataSource securityDataSource = new ComboPooledDataSource();

        // set the jdbc driver class

        try {
            securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
        } catch (PropertyVetoException exc) {
            throw new RuntimeException(exc);
        }

        // log the connection props
        // for sanity's sake, log this info
        // just to make sure we are REALLY reading data from properties file

        logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
        logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));

        // set database connection props

        securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        securityDataSource.setUser(env.getProperty("jdbc.user"));
        securityDataSource.setPassword(env.getProperty("jdbc.password"));

        // set connection pool props

        securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));

        securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));

        securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));

        securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));

        return securityDataSource;
    }

    // need a helper method
    // read environment property and convert to int

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }
}