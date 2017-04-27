package com.itmuch.cloud.study.user.feign;

import com.itmuch.cloud.study.user.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);

  // 该请求不会成功
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public User get0(User user);

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public User get1(@RequestParam("id") Long id, @RequestParam("username") String username);

  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public User get2(@RequestParam Map<String, Object> map);

  @RequestMapping(value = "/post", method = RequestMethod.POST)
  public User post(@RequestBody User user);
}
