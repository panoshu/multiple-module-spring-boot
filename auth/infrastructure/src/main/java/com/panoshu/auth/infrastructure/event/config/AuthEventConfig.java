package com.panoshu.auth.infrastructure.event.config;


import com.panoshu.common.infrastructure.event.EventBus;
import com.panoshu.common.infrastructure.event.EventDeliveryRuleConfiguration;
import com.panoshu.common.infrastructure.event.IEventChannel;
import com.panoshu.common.infrastructure.event.channel.SpringEventChannel;
import com.panoshu.auth.domain.user.events.UserEventFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 22:08
 **/

@Configuration
@EnableConfigurationProperties(EventDeliveryRuleConfiguration.class)
public class AuthEventConfig {

  @Bean
  public UserEventFactory userEventFactory() {
    return new UserEventFactory();
  }

  @Bean
  public SpringEventChannel springEventChannel(ApplicationEventPublisher applicationEventPublisher) {
    return new SpringEventChannel(applicationEventPublisher);
  }

  @Bean
  public EventBus eventBus(
    EventDeliveryRuleConfiguration routingConfig,
    SpringEventChannel springEventChannel) {
    List<IEventChannel> channels = Collections.singletonList(
      springEventChannel
    );
    return new EventBus(routingConfig, channels);
  }
}
