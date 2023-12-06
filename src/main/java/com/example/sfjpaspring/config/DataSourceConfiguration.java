package com.example.sfjpaspring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Primary
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean
    entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            DataSource dataSource
    ) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.sfjpaspring.entity")
                .build();
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Bean(name = "replicaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.replica")
    public DataSource replicaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "readOnlyEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean readOnlyEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("replicaDataSource") DataSource replicaDataSource
    ) {
        return builder
                .dataSource(replicaDataSource)
                .packages("com.example.sfjpaspring.entity")
                .build();
    }

    @Bean(name = "readOnlyTransactionManager")
    public PlatformTransactionManager readOnlyTransactionManager(
            @Qualifier("readOnlyEntityManagerFactory") EntityManagerFactory readOnlyEntityManagerFactory
    ) {
        return new JpaTransactionManager(readOnlyEntityManagerFactory);
    }
}
