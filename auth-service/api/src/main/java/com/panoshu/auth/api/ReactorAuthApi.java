package com.panoshu.auth.api;

import org.springframework.web.bind.annotation.GetMapping;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/6 21:32
 **/

@ReactiveFeignClient(name = "auth", url = "127.0.0.1:8089", path = "auth")
public interface ReactorAuthApi {

  @GetMapping(value = "hello")
  Mono<String> hello();

}
