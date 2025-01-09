package com.example.consumer.adapter;

import com.example.provider.api.ReactorApi;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/6 22:19
 **/

@RestController
public class ReactorConsumerController {

  @Resource
  private ReactorApi reactorApi;

  @GetMapping("dddd")
  public Mono<String> getReactor() {
    return reactorApi.hello();
  }

}
