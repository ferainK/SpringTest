package com.example.springTest.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String url = request.getRequestURI();
    URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI())
            .query(request.getQueryString()).build().toUri();
    log.info("request url : {}", url);

    boolean hasAnnotation = checkAnnotation(handler, Auth.class);
    if (hasAnnotation){
      String query = uri.getQuery();
      if (query.equals("name=ferain")){
        return true;
      }
    }
    throw new AuthException();
  }

  private boolean checkAnnotation(Object handler, Class clazz){
    // HTML, JS, CSS request check
    if(handler instanceof ResourceHttpRequestHandler){
      return true;
    }

    // annotation check
    HandlerMethod handlerMethod = (HandlerMethod) handler;
    if(handlerMethod.getMethodAnnotation(clazz) != null || handlerMethod.getBeanType().getAnnotation(clazz) != null){
      return true;
    }

    return false;
  }
}
