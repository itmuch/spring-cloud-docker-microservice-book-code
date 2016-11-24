package com.itmuch.cloud.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.stereotype.Component;

import com.netflix.zuul.FilterFileManager;
import com.netflix.zuul.FilterLoader;
import com.netflix.zuul.groovy.GroovyCompiler;
import com.netflix.zuul.groovy.GroovyFileFilter;
import com.netflix.zuul.monitoring.MonitoringHelper;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {
  public static void main(String[] args) {
    SpringApplication.run(ZuulApplication.class, args);
  }

  @Component
  public static class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
      MonitoringHelper.initMocks();
      FilterLoader.getInstance().setCompiler(new GroovyCompiler());
      try {
        FilterFileManager.setFilenameFilter(new GroovyFileFilter());

        String basePath = "D:/写书/code/microservice-gateway-zuul-filter-groovy/src/main/filters/";

        FilterFileManager.init(1, basePath + "pre", basePath + "post");
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
  }
}
