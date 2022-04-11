package com.example.springTest.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/api31/*") //@Component 로 지정하면 글로벌 적용
public class GlobalFilter implements Filter {
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    //데이터 캐쉬 처리
    ContentCachingRequestWrapper httpServletRequest = new ContentCachingRequestWrapper((HttpServletRequest) request); //한번 읽으면 휘발되기 떄문에 Caching기능을 사용해야함
    ContentCachingResponseWrapper httpServletResponse = new ContentCachingResponseWrapper((HttpServletResponse) response); //한번 읽으면 휘발되기 떄문에 Caching기능을 사용해야함

    //서버 실행
    chain.doFilter(request, response);

    //전/후처리
    String url = httpServletRequest.getRequestURI();
    String reqContent = new String(httpServletRequest.getContentAsByteArray());
    log.info("request url : {}, request body : {}", url, reqContent);

    String resContent = new String((httpServletResponse.getContentAsByteArray()));
    int httpStatusCode = httpServletResponse.getStatus();
    log.info("response status : {}, response body : {}", httpStatusCode, resContent);
  }
}
