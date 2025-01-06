package com.example.provider.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/6 21:32
 **/

@ReactiveFeignClient(name = "provider", url = "127.0.0.1:8080")
public interface ReactorApi {

  @GetMapping(value = "hello")
  Mono<String> hello();

}
