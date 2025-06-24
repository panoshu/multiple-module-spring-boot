package com.panoshu.common.infrastructure.event;

import com.panoshu.common.domain.DomainEvent;
import com.panoshu.common.types.Identifier;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 21:04
 **/

@Slf4j
public class EventBus implements IEventBus {
  private final EventDeliveryRuleConfiguration deliveryRuleConfiguration;
  private final Map<String, IEventChannel> registeredChannels;

  public EventBus(EventDeliveryRuleConfiguration deliveryRuleConfiguration, List<IEventChannel> channels) {
    this.deliveryRuleConfiguration = deliveryRuleConfiguration;
    this.registeredChannels = new ConcurrentHashMap<>();

    channels.forEach(this::registerChannel);
    // this.channels = channels.stream().collect(Collectors.toMap(EventChannel::getChannelName, Function.identity()));
  }

  @Override
  public void registerChannel(IEventChannel channel) {
    if (registeredChannels.containsKey(channel.getChannelName())) {
      log.warn("Duplicate event channel name detected: {}. Only the first instance will be used.", channel.getChannelName());
    }
    this.registeredChannels.put(channel.getChannelName(), channel);
    log.info("Registered event channel: {}", channel.getChannelName());
  }

  @Override
  public void dispatch(DomainEvent<Identifier> event) {
    Set<String> targetChannels = deliveryRuleConfiguration.getChannelsForEvent(event.eventType());

    if (targetChannels == null || targetChannels.isEmpty()) {
      log.info("No configured channels for event: {} (and no default channels or default channels are empty). Event {} will not be dispatched to any channel.",
        event.eventType(), event.eventId());
      return;
    }

    log.info("Dispatching event {} ({}) to channels: {}", event.eventId(), event.eventType(), targetChannels);

    targetChannels.forEach(channelName -> {
      IEventChannel channel = this.registeredChannels.get(channelName);
      if (channel != null) {
        try {
          channel.dispatch(event);
        } catch (Exception e) {
          log.error("Failed to dispatch event {} to channel {}: {}", event.eventId(), channelName, e.getMessage(), e);
        }
      } else {
        log.warn("Channel '{}' configured for event {} but not registered in EventBus. Event {} will not be dispatched to this channel.",
          channelName, event.eventType(), event.eventId());
      }
    });
  }
}
