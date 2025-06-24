package com.panoshu.common.infrastructure.event;

import java.util.List;

/**
 * DeliveryRule
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/19 22:53
 **/

public record DeliveryRule(String eventType, List<String> channels) {
  // 验证规则
  public DeliveryRule {
    if (eventType == null || eventType.isBlank()) {
      throw new IllegalArgumentException("EventType could not be null or empty");
    }

    if (channels == null || channels.isEmpty()) {
      throw new IllegalArgumentException("Channels of EventType: {} could not be null or empty");
    }
  }
}
