package com.panoshu.common.infrastructure.event;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 20:39
 **/

@ConfigurationProperties(prefix = "event")
@Slf4j
public class EventDeliveryRuleConfiguration {

  private final Set<String> defaultChannels;
  private final Map<String, Set<String>> eventChannels;

  @ConstructorBinding
  public EventDeliveryRuleConfiguration(
    @DefaultValue List<String> defaultDeliveryChannels,
    @DefaultValue List<DeliveryRule> deliveryRules
  ) {
    this.defaultChannels = validateDefaultChannels(defaultDeliveryChannels);
    this.eventChannels = validateDeliveryRules(deliveryRules);
    log.info("配置加载: 默认渠道 {} 个 | 规则 {} 条",
      defaultChannels.size(), eventChannels.size());
  }

  private Set<String> validateDefaultChannels(List<String> channels) {
    if (channels == null || channels.isEmpty()) {
      throw new IllegalArgumentException(
        "'event.default-delivery-channels' 必须包含至少一个有效渠道"
      );
    }

    Set<String> validChannels = channels.stream()
      .filter(Objects::nonNull)
      .filter(channel -> !channel.isBlank())
      .collect(Collectors.toCollection(LinkedHashSet::new));

    if (validChannels.isEmpty()) {
      throw new IllegalArgumentException(
        "'event.default-delivery-channels' 中所有渠道都无效"
      );
    }

    return Set.copyOf(validChannels);
  }

  private Map<String, Set<String>> validateDeliveryRules(List<DeliveryRule> rules) {
    if (rules == null || rules.isEmpty()) {
      return Collections.emptyMap();
    }

    for (int i = 0; i < rules.size(); i++) {
      validateRule(rules.get(i), i + 1);
    }

    return rules.stream()
      .collect(Collectors.toMap(
        DeliveryRule::eventType, rule -> new HashSet<>(rule.channels()),
        (existing, replacement) -> replacement,
        LinkedHashMap::new
      ));
  }

  private void validateRule(DeliveryRule rule, int ruleIndex) {
    if (rule == null) {
      throw new IllegalArgumentException("规则 #" + ruleIndex + ": 不能为null");
    }

    // 验证事件类型
    if (rule.eventType() == null || rule.eventType().isBlank()) {
      throw new IllegalArgumentException("规则 #" + ruleIndex + ": 事件类型不能为空");
    }

    // 验证渠道列表
    List<String> channels = rule.channels();
    if (channels == null || channels.isEmpty()) {
      throw new IllegalArgumentException("规则 #" + ruleIndex + ": 渠道列表不能为空");
    }

    // 验证每个渠道
    for (int i = 0; i < channels.size(); i++) {
      if (channels.get(i) == null || channels.get(i).isBlank()) {
        throw new IllegalArgumentException("规则 #" + ruleIndex + " 的渠道 #" + (i+1) + ": 渠道名称不能为空或空白");
      }
    }
  }

  public Set<String> getChannelsForEvent(String eventType) {
    return eventChannels.getOrDefault(eventType, defaultChannels);
  }
}
