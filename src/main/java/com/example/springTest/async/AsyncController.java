package com.example.springTest.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api5")
public class AsyncController {
  private AsyncService asyncService;

  public AsyncController(AsyncService asyncService) {
    this.asyncService = asyncService;
  }
  @GetMapping("/hello")
  public CompletableFuture hello(){
    return asyncService.run();
  }
}
