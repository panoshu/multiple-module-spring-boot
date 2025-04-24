package com.example.consumer.adapter;

import com.example.provider.api.ReactorApi;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/6 22:19
 **/

@RestController
@RequiredArgsConstructor
public class ReactorConsumerController {

  private final ReactorApi reactorApi;

  @GetExchange("dddd")
  public Mono<String> getReactor() {
    return reactorApi.hello().map(value -> "Consumer: " + value);
  }

}
