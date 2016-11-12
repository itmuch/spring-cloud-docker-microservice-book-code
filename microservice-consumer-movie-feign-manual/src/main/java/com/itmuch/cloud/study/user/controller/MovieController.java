package com.itmuch.cloud.study.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.cloud.netflix.feign.support.ResponseEntityDecoder;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.study.user.entity.User;
import com.itmuch.cloud.study.user.feign.UserFeignClient;

import feign.Client;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;

@Import(FeignClientsConfiguration.class)
@RestController
public class MovieController {
  private UserFeignClient userUserFeignClient;

  private UserFeignClient adminUserFeignClient;

  @Autowired
  public MovieController(ResponseEntityDecoder decoder, SpringEncoder encoder, Client client) {
    this.userUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder)
        .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1")).target(UserFeignClient.class, "http://microservice-provider-user/");
    this.adminUserFeignClient = Feign.builder().client(client).encoder(encoder).decoder(decoder)
        .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
        .target(UserFeignClient.class, "http://microservice-provider-user/");
  }

  @GetMapping("/user-user/{id}")
  public User findByIdUser(@PathVariable Long id) {
    return this.userUserFeignClient.findById(id);
  }

  @GetMapping("/user-admin/{id}")
  public User findByIdAdmin(@PathVariable Long id) {
    return this.adminUserFeignClient.findById(id);
  }
}
