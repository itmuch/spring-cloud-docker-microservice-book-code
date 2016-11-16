package com.itmuch.cloud.study.user.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itmuch.cloud.study.user.entity.User;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "microservice-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public User findById(@PathVariable("id") Long id);
}

/**
 * 该类是Feign Client的回退类
 * @author 周立
 */
class FeignClientFallback implements UserFeignClient {
  @Override
  public User findById(Long id) {
    User user = new User();
    user.setId(-1L);
    user.setUsername("默认用户");
    return user;
  }
}

/**
 * fallbackFactory，该类需要实现FallbackFactory接口.
 * 在其中覆写create方法，该方法需要返回FeignClient的fallback类。
 * The fallback factory must produce instances of fallback classes that
 * implement the interface annotated by {@link FeignClient}.
 * B.T.W，可以将将feign包的日志级别设置成DEBUG，此时feign.hystrix.FallbackFactory.Default<T> 将会打印一些回退原因日志。
 * @author 周立
 */
@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
  private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

  @Override
  public UserFeignClient create(Throwable cause) {
    // 先打印拦截到的异常
    FeignClientFallbackFactory.LOGGER.info("fallback; reason was: {}", cause.getMessage());
    return new FeignClientFallback();
  }
}