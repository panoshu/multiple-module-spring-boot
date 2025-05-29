package com.panoshu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

/**
 * auth application
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/5/29 23:09
 **/

@EnableReactiveFeignClients
@SpringBootApplication
public class AuthApplication {
  public static void main(String[] args){
    SpringApplication.run(AuthApplication.class, args);
  }
}
