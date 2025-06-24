package com.panoshu.common.infrastructure.event;

import lombok.Getter;
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
@Getter
@Slf4j
public class EventDeliveryRuleConfiguration {

  private final Set<String> defaultChannels;
  private final Map<String, Set<String>> eventChannels;

  @ConstructorBinding
  public EventDeliveryRuleConfiguration(
    @DefaultValue List<String> defaultDeliveryChannels,
    @DefaultValue Map<String, List<String>> deliveryRules
  ) {
    this.defaultChannels = processDefaultChannels(defaultDeliveryChannels);
    this.eventChannels = processDeliveryRules(deliveryRules);
    log.info("配置加载: 默认渠道 {} 个 | 事件规则 {} 条",
      defaultChannels.size(), eventChannels.size());
  }

  private Set<String> processDefaultChannels(List<String> channels) {
    if (channels == null || channels.isEmpty()) {
      throw new IllegalArgumentException("'event.default-delivery-channels' 必须包含至少一个有效渠道");
    }

    Set<String> validChannels = channels.stream()
      .filter(Objects::nonNull)
      .map(String::trim)
      .filter(channel -> !channel.isEmpty())
      .collect(Collectors.toCollection(LinkedHashSet::new));

    if (validChannels.isEmpty()) {
      throw new IllegalArgumentException("'event.default-delivery-channels' 中所有渠道都无效");
    }

    return Collections.unmodifiableSet(validChannels);
  }

  private Map<String, Set<String>> processDeliveryRules(Map<String, List<String>> rules) {
    if (rules == null || rules.isEmpty()) {
      return Collections.emptyMap();
    }

    Map<String, Set<String>> result = new LinkedHashMap<>();
    int ruleCount = 0;

    for (Map.Entry<String, List<String>> entry : rules.entrySet()) {
      ruleCount++;
      String eventType = entry.getKey();
      List<String> channels = entry.getValue();

      // 校验事件类型
      validateEventType(eventType, ruleCount);

      // 处理渠道
      Set<String> validChannels = processChannels(channels, eventType, ruleCount);
      result.put(eventType, Collections.unmodifiableSet(validChannels));
    }

    return Collections.unmodifiableMap(result);
  }

  private void validateEventType(String eventType, int ruleIndex) {
    if (eventType == null || eventType.isBlank()) {
      throw new IllegalArgumentException(
        "规则 #" + ruleIndex + ": 事件类型不能为空"
      );
    }
  }

  private Set<String> processChannels(List<String> channels, String eventType, int ruleIndex) {
    if (channels == null || channels.isEmpty()) {
      throw new IllegalArgumentException(
        "规则 #" + ruleIndex + " (事件类型: " + eventType + "): 渠道列表不能为空"
      );
    }

    Set<String> validChannels = new LinkedHashSet<>();
    int channelIndex = 0;

    for (String channel : channels) {
      channelIndex++;
      if (channel == null || channel.isBlank()) {
        throw new IllegalArgumentException(
          "规则 #" + ruleIndex + " (事件类型: " + eventType + ") 的渠道 #" +
            channelIndex + ": 渠道名称不能为空或空白"
        );
      }

      String trimmedChannel = channel.trim();
      if (!validChannels.add(trimmedChannel)) {
        log.warn("规则 #{} (事件类型: {}) 的渠道 #{}: 渠道 '{}' 重复，已忽略",
          ruleIndex, eventType, channelIndex, trimmedChannel);
      }
    }

    return validChannels;
  }

  public Set<String> getChannelsForEvent(String eventType) {
    if (eventType == null || eventType.isBlank()) {
      throw new IllegalArgumentException("事件类型不能为空");
    }
    return eventChannels.getOrDefault(eventType, defaultChannels);
  }
}
