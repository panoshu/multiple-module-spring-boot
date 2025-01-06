package com.example.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;

/**
 * description
 *
 * @author <a href="mailto: panoshu@gmail.com">panoshu</a>
 * @version 1.0
 * @since 2025/1/2 10:26
 */

@EnableReactiveFeignClients
@SpringBootApplication
public class ProviderApplication {
  public static void main(String[] args){
    SpringApplication.run(ProviderApplication.class, args);
  }
}
