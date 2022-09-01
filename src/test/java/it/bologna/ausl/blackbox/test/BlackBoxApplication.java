package it.bologna.ausl.blackbox.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"it.bologna.ausl", "it.nextsw"})
@EntityScan("it.bologna.ausl.model.entities")
@EnableJpaRepositories(basePackages = {"it.bologna.ausl.blackbox.repositories", "it.bologna.ausl.blackbox.test.repositories"})
public class BlackBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackBoxApplication.class, args);
    }
}
