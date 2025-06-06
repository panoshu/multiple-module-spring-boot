package com.panoshu.auth.api;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/5 12:46
 **/

@HttpExchange("auth")
public interface AuthApi {

  @GetExchange("api-a")
  Mono<String> apiAlpha();
}
