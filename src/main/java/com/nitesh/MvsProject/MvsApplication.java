package com.nitesh.MvsProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MvsApplication {
    @Autowired
    ApplicationContext ctx;

    public static void main(String[] args) {
        System.out.println("com.nitesh.MvsProject.MvsApplication :: start ::");
        ApplicationContext ctx = SpringApplication.run(MvsApplication.class, args);

    }

}
