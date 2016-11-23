package com.itmuch.cloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileUploadApplication {
  public static void main(String[] args) {
    SpringApplication.run(FileUploadApplication.class, args);
  }
}
