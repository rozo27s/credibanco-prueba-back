package com.credibanco.assessment.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.credibanco.assessment"})
@EnableJpaRepositories(basePackages = {
        "com.credibanco.assessment.framework.jpa.crudrepository" })
@EntityScan(basePackages = { 
        "com.credibanco.assessment.card.model",
        "com.credibanco.assessment.transaction.model" })
public class ServiceApiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApiRestApplication.class, args);
    }

}
