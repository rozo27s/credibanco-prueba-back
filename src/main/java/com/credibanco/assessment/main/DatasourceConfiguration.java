package com.credibanco.assessment.main;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.credibanco.assessment.framework.constants.ConfigurationConstants;

/**
 * 
 * @author ajrozo
 *
 */
@Configuration
public class DatasourceConfiguration {

    @Value(ConfigurationConstants.DATASOURSE_URL)
    private String url;

    @Bean(ConfigurationConstants.SPRING_DATASOURCE_BEAN)
    @ConfigurationProperties(prefix = ConfigurationConstants.SPRING_DATASOURCE)
    public DataSource dataSourceEbusiness() {
        return DataSourceBuilder
                .create()
                .url(url)
                .build();
    }

}
