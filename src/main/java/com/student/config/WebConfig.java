package com.student.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "com.student")
public class WebConfig implements WebMvcConfigurer {

    // PostgreSQL Database Connection
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName(
                "org.postgresql.Driver"
        );

        dataSource.setUrl(
                "jdbc:postgresql://localhost:5432/studentdb"
        );

        dataSource.setUsername("postgres");

        dataSource.setPassword("dani06");

        return dataSource;
    }

    // Hibernate + JPA Configuration
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        factory.setDataSource(dataSource);

        factory.setPackagesToScan(
                "com.student.entity"
        );

        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();

        factory.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();

        properties.put(
                "hibernate.dialect",
                "org.hibernate.dialect.PostgreSQL10Dialect"
        );

        properties.put(
                "hibernate.hbm2ddl.auto",
                "update"
        );

        properties.put(
                "hibernate.show_sql",
                "true"
        );

        factory.setJpaProperties(properties);

        return factory;
    }

    // Transaction Manager
    @Bean
    public PlatformTransactionManager transactionManager(
            LocalContainerEntityManagerFactoryBean entityManagerFactory) {

        return new JpaTransactionManager(
                entityManagerFactory.getObject()
        );
    }

    // JSP View Resolver
    @Override
    public void configureViewResolvers(
            ViewResolverRegistry registry) {

        registry.jsp(
                "/WEB-INF/views/",
                ".jsp"
        );
    }
}