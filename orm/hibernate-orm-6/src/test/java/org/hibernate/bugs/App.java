package org.hibernate.bugs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "org.hibernate.bugs")
@EnableJpaRepositories("org.hibernate.bugs")
@EntityScan("org.hibernate.bugs")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}