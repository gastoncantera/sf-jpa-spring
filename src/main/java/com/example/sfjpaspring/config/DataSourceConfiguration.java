package com.example.sfjpaspring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DataSourceConfiguration {

    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "replicaDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.replica")
    public DataSource replicaDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public TransactionRoutingDataSource routingDatasource(
            @Qualifier("dataSource") DataSource dataSource,
            @Qualifier("replicaDataSource") DataSource replicaDataSource
    ) {
        return new TransactionRoutingDataSource(dataSource, replicaDataSource);
    }

}

class TransactionRoutingDataSource extends AbstractRoutingDataSource {
    public TransactionRoutingDataSource(DataSource mainDataSource, DataSource replicaDataSource) {
        this.setTargetDataSources(new HashMap<Object, Object>() {{
            put(DataSourceType.READ_WRITE, mainDataSource);
            put(DataSourceType.READ_ONLY, replicaDataSource);
        }});
        this.setDefaultTargetDataSource(mainDataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager
                .isCurrentTransactionReadOnly()
                ? DataSourceType.READ_ONLY
                : DataSourceType.READ_WRITE;
    }
}

enum DataSourceType {
    READ_ONLY,
    READ_WRITE
}