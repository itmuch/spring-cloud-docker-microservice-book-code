package com.itmuch.cloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * 该类为Ribbon的配置类，注意：该类不能与@ComponentScan扫描
 * @author Administrator
 *
 */
@Configuration
public class RibbonConfiguration {
  @Bean
  public IRule ribbonRule() {
    return new RandomRule();
  }
}