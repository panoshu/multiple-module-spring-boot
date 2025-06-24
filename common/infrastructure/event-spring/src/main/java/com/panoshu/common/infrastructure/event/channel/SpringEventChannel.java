package com.panoshu.common.infrastructure.event.channel;

import com.panoshu.common.domain.DomainEvent;
import com.panoshu.common.infrastructure.event.IEventChannel;
import com.panoshu.common.types.Identifier;
import org.springframework.context.ApplicationEventPublisher;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 21:55
 **/

public class SpringEventChannel implements IEventChannel {
  private final ApplicationEventPublisher applicationEventPublisher;

  // Spring 会自动注入 ApplicationEventPublisher
  public SpringEventChannel(ApplicationEventPublisher applicationEventPublisher) {
    this.applicationEventPublisher = applicationEventPublisher;
  }

  @Override
  public void dispatch(DomainEvent<Identifier> event) {
    System.out.printf("Dispatched event %s to Spring Application Event Publisher for type: %s", event.eventId(), event.eventType());
    applicationEventPublisher.publishEvent(event);
  }
}
