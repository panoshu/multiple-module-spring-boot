package com.panoshu.common.infrastructure.event;

import java.util.Set;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/22 22:33
 **/

public interface IEventDeliveryRuleConfiguration {
  /**
   * 根据事件类型获取投递渠道集合
   */
  Set<String> getChannelsForEvent(String eventType);
}
