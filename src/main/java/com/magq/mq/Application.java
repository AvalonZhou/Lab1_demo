package com.magq.mq;

import com.magq.mq.client.Stage;
import com.magq.mq.client.User;
import com.magq.mq.controller.Broker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:spring-config.xml"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, ValidationAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
