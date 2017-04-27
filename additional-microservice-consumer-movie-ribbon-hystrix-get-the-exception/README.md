# 说明
很多时候，我们希望获得造成fallback的原因。此时，可查看本示例。
事实上非常简单，即：
```java
@HystrixCommand(fallbackMethod = "findByIdFallback")
@GetMapping("/user/{id}")
public User findById(@PathVariable Long id) {
  return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
}

/**
 * 如果想要获得导致fallback的原因，只需在fallback方法上添加Throwable参数即可。
 * @param id ID
 * @param throwable 异常
 * @return 用户
 */
public User findByIdFallback(Long id, Throwable throwable) {
  LOGGER.error("进入回退方法，异常：", throwable);
  User user = new User();
  user.setId(-1L);
  user.setName("默认用户");
  return user;
}
```