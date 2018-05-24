package com.project.job;


import com.project.job.controller.JobController;
import com.project.job.dao.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManagerFactory;

@EnableAutoConfiguration
@ComponentScan({"com.project.job.controller", "com.project.job.dao", "com.project.job.configuration"})
public class Application {


    @Autowired
    JobRepository jobRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
