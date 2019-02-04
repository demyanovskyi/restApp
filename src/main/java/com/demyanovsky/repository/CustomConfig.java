/*
package com.demyanovsky.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class CustomConfig {

    @Bean
    @Profile("test")
    DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.H2)
                .addScript("create-user-schema.sql")
                .addScript("create-product-schema.sql")
                .addScript("create-user-schema.sql")
                .addScript("create-user-schema.sql")
                .build();
    }
}
*/
