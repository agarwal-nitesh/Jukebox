package com.nitesh.jukebox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JukeBoxApplication {
    @Autowired
    ApplicationContext ctx;

    public static void main(String[] args) {
        System.out.println("com.nitesh.jukebox.JukeBoxApplication :: start ::");
        ApplicationContext ctx = SpringApplication.run(JukeBoxApplication.class, args);

    }

}
