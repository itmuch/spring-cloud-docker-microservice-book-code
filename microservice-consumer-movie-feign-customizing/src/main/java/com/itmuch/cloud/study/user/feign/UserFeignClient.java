package com.itmuch.cloud.study.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

import com.itmuch.cloud.config.FeignConfiguration;
import com.itmuch.cloud.study.user.entity.User;

import feign.Param;
import feign.RequestLine;

/**
 * 使用@FeignClient的configuration属性，指定feign的配置类。
 * @author 周立
 */
@FeignClient(name = "microservice-provider-user", configuration = FeignConfiguration.class)
public interface UserFeignClient {
  /**
   * 使用feign自带的注解@RequestLine
   * @see https://github.com/OpenFeign/feign
   * @param id 用户id
   * @return 用户信息
   */
  @RequestLine("GET /{id}")
  public User findById(@Param("id") Long id);
}
