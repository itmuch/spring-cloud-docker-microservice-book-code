package com.itmuch.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
public class FeignLogConfiguration {
  @Bean
  Logger.Level feignLoggerLevel() {
    return Logger.Level.BASIC;
  }
}
