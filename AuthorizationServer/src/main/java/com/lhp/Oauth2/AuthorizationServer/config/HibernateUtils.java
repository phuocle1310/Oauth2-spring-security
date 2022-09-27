package com.lhp.Oauth2.AuthorizationServer.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


@Configuration
public class HibernateUtils {
    @Autowired
    private Environment env;

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setHibernateProperties(getProperties());
        factory.setPackagesToScan("com.lhp.Oauth2.AuthorizationServer.pojo");

        return factory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        
        return dataSource;
    }

    public Properties getProperties() {
        Properties props = new Properties();
        props.setProperty(AvailableSettings.DIALECT, env.getProperty("spring.jpa.properties.hibernate.dialect"));
        props.setProperty(AvailableSettings.SHOW_SQL, env.getProperty("spring.jpa.show-sql"));

        return props;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager trans = new HibernateTransactionManager();
        trans.setSessionFactory(this.getSessionFactory().getObject());
        
        return trans;
    }
}
