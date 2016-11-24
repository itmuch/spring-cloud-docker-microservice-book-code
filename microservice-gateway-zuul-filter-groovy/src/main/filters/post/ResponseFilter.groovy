package com.itmuch.cloud.study.post

import org.slf4j.LoggerFactory

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext

class ResponseFilter extends ZuulFilter{
  @Override
  String filterType() {
    return "post"
  }

  @Override
  int filterOrder() {
    return 2000
  }

  @Override
  boolean shouldFilter() {
    return true
  }

  @Override
  Object run() {
    print ("response code is : " + RequestContext.currentContext().response().status());
  }
}
