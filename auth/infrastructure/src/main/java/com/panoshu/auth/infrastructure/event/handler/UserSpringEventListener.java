package com.panoshu.auth.infrastructure.event.handler;

import com.panoshu.iam.domain.user.events.UserRegisteredEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 22:03
 **/

@Component
public class UserSpringEventListener {

  @EventListener
  public void handleUserRegisteredEvent(UserRegisteredEvent event) {
    System.out.printf("[Spring Listener] Received UserRegisteredEvent via Spring Event mechanism for user: %s, ID: %s", event.username(), event.eventId());
    //TODO: 这里可以执行与 Spring 事务、缓存、邮件通知等集成的业务逻辑
  }
}
