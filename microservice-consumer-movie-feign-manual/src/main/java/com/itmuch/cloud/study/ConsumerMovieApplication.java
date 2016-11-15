package com.itmuch.cloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerMovieApplication {
  public static void main(String[] args) {
    SpringApplication.run(ConsumerMovieApplication.class, args);
  }
}
