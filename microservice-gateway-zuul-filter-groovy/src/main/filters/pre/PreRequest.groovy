package com.itmuch.cloud.study.pre;

import javax.servlet.http.HttpServletRequest;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

class PreRequest extends ZuulFilter {
  @Override
  String filterType() {
    return "pre"
  }

  @Override
  int filterOrder() {
    return 1000
  }

  @Override
  boolean shouldFilter() {
    return true
  }

  @Override
  Object run() {
    HttpServletRequest request = RequestContext.currentContext().request();
    print(String.format("send %s request to %s", request.getMethod(), request.getRequestURL().toString()));
    return null
  }
}