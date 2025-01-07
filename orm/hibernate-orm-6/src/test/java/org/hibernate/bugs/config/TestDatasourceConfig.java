package org.hibernate.bugs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.containers.wait.strategy.Wait;
import org.awaitility.Awaitility;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.time.Duration;

@TestConfiguration
public class TestDatasourceConfig {

    @Autowired
    private Environment env;

//    private final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15.2");

    public TestDatasourceConfig() {
//        container.waitingFor(Wait.defaultWaitStrategy());
//        container.start();
    }

    @Bean
    public DataSource dataSource() {
//        Awaitility.await().atMost(Duration.ofSeconds(60)).until(container::isRunning);
//
//        SimpleDriverDataSource dataSourceBuilder = new SimpleDriverDataSource();
//        dataSourceBuilder.setDriverClass(org.postgresql.Driver.class);
//        dataSourceBuilder.setUrl(container.getJdbcUrl());
//        dataSourceBuilder.setUsername(container.getUsername());
//        dataSourceBuilder.setPassword(container.getPassword());
//        return dataSourceBuilder;

        SimpleDriverDataSource dataSourceBuilder = new SimpleDriverDataSource();
        dataSourceBuilder.setDriverClass(org.h2.Driver.class);
        dataSourceBuilder.setUrl(env.getProperty("spring.datasource.url"));
        dataSourceBuilder.setUsername(env.getProperty("spring.datasource.username"));
        dataSourceBuilder.setPassword(env.getProperty("spring.datasource.password"));
        return dataSourceBuilder;
    }
}