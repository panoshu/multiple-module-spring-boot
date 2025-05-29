package com.panoshu.auth.adapter;

import com.panoshu.auth.api.AuthApi;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/5 12:53
 **/

@RestController
public class AuthController implements AuthApi {

  @Override
  public Mono<String> apiAlpha() {
    return Mono.just("demo controller api alpha");
  }
}
