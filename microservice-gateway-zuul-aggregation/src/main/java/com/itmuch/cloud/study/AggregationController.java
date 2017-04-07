package com.itmuch.cloud.study;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;
import rx.Observer;

import java.util.HashMap;

@RestController
public class AggregationController {
  public static final Logger LOGGER = LoggerFactory.getLogger(ZuulApplication.class);

  @Autowired
  private AggregationService aggregationService;

  @GetMapping("/aggregate/{id}")
  public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
    Observable<HashMap<String, User>> result = this.aggregateObservable(id);
    return this.toDeferredResult(result);
  }

  public Observable<HashMap<String, User>> aggregateObservable(Long id) {
    // 合并两个或者多个Observables发射出的数据项，根据指定的函数变换它们
    return Observable.zip(
            this.aggregationService.getUserById(id),
            this.aggregationService.getMovieUserByUserId(id),
            (user, movieUser) -> {
              HashMap<String, User> map = Maps.newHashMap();
              map.put("user", user);
              map.put("movieUser", movieUser);
              return map;
            }
    );
  }

  public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
    DeferredResult<HashMap<String, User>> result = new DeferredResult<>();
    // 订阅
    details.subscribe(new Observer<HashMap<String, User>>() {
      @Override
      public void onCompleted() {
        LOGGER.info("完成...");
      }

      @Override
      public void onError(Throwable throwable) {
        LOGGER.error("发生错误...", throwable);
      }

      @Override
      public void onNext(HashMap<String, User> movieDetails) {
        result.setResult(movieDetails);
      }
    });
    return result;
  }
}