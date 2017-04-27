# 说明

## 简介
多数场景下，发生业务异常时，我们并不想触发fallback。此时要怎么办呢？
Hystrix有个HystrixBadRequestException类，这是一种特殊的异常，当该异常发生时，并不会触发回退。
因此，我们可以用将我们自定义的业务异常集成该异常类，从而实现业务异常不回退的效果。


## Going Far
事实上，@HystrixCommand注解有个ignoreExceptions属性，我们也可借助该属性来配置不想fallback的异常类。示例：
```java
@HystrixCommand(fallbackMethod = "findByIdFallback", ignoreExceptions = {IllegalArgumentException.class, MyBusinessException.class})
@GetMapping("/user/{id}")
public User findById(@PathVariable Long id) {
  return this.userService.findById(id);
}
```