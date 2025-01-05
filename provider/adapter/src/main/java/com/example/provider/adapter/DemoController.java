package com.example.provider.adapter;

import com.example.provider.api.IDemoApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/1/5 12:53
 **/

@RestController
public class DemoController implements IDemoApi {

  @Override
  public String apiAlpha() {
    return "demo controller api alpha";
  }
}
