package com.panoshu.auth.adapter;

import com.panoshu.auth.api.ReactorAuthApi;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/6 21:37
 **/

@RestController
@HttpExchange("auth")
public class ReactorAuthController implements ReactorAuthApi {

  @Override
  public Mono<String> hello() {
    return Mono.just("Hello World! I'm Reactor Feign");
  }
}
