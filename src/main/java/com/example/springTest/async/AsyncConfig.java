package com.example.springTest.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

//비동기는.. 참고만 하자 잘 쓰지않음
//같은 클래스에서는 비동기처리가 어렵다는점 참고
@Configuration
public class AsyncConfig {
  @Bean("async-thread")
  public Executor asyncThread(){
    ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
    threadPoolTaskExecutor.setMaxPoolSize(100);
    threadPoolTaskExecutor.setCorePoolSize(10);
    threadPoolTaskExecutor.setQueueCapacity(10);
    threadPoolTaskExecutor.setThreadNamePrefix("Async-");
    return threadPoolTaskExecutor;
  }
}
