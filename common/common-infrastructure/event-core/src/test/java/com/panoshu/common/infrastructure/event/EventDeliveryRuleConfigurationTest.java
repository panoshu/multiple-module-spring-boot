package com.panoshu.common.infrastructure.event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

/**
 * EventDeliveryRuleConfiguration 单元测试
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/24 23:14
 **/

@DisplayName("EventDeliveryRuleConfiguration 单元测试")
class EventDeliveryRuleConfigurationTest {

  @Nested
  @DisplayName("配置初始化测试")
  class InitializationTests {

    @Test
    @DisplayName("有效配置应正确加载")
    void validConfiguration_ShouldLoadCorrectly() {
      // Given
      List<String> defaultChannels = List.of("email", "sms");
      Map<String, List<String>> rules = Map.of(
        "OrderCreated", List.of("push"),
        "PaymentReceived", List.of("sms", "webhook")
      );

      // When
      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        defaultChannels,
        rules
      );

      // Then
      assertThat(config.getDefaultChannels())
        .containsExactlyInAnyOrder("email", "sms");

      assertThat(config.getEventChannels())
        .hasSize(2)
        .contains(
          entry("OrderCreated", Set.of("push")),
          entry("PaymentReceived", Set.of("sms", "webhook"))
        );
    }

    @Test
    @DisplayName("空规则列表应创建空映射")
    void emptyRulesList_ShouldCreateEmptyMap() {
      // When
      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        List.of("email"),
        Collections.emptyMap()
      );

      // Then
      assertThat(config.getEventChannels()).isEmpty();
    }
  }

  @Nested
  @DisplayName("默认渠道处理测试")
  class DefaultChannelsTests {

    @Test
    @DisplayName("null默认渠道应抛出异常")
    void nullDefaultChannels_ShouldThrowException() {
      assertThatThrownBy(() ->
        new EventDeliveryRuleConfiguration(null, Collections.emptyMap()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("'event.default-delivery-channels' 必须包含至少一个有效渠道");
    }

    @Test
    @DisplayName("含空白值的默认渠道应过滤无效项")
    void defaultChannelsWithBlanks_ShouldBeFiltered() {
      // When
      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        List.of(" email ", "  ", "sms", ""),
        Collections.emptyMap()
      );

      // Then
      assertThat(config.getDefaultChannels())
        .containsExactlyInAnyOrder("email", "sms");
    }

    @Test
    @DisplayName("全无效默认渠道应抛出异常")
    void allInvalidDefaultChannels_ShouldThrowException() {
      // 使用可变列表创建包含 null 的测试数据
      List<String> invalidChannels = new ArrayList<>();
      invalidChannels.add(" ");
      invalidChannels.add("");
      invalidChannels.add(null);

      // When & Then
      assertThatThrownBy(() ->
        new EventDeliveryRuleConfiguration(invalidChannels, Collections.emptyMap()))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("'event.default-delivery-channels' 中所有渠道都无效");
    }
  }

  @Nested
  @DisplayName("规则处理测试")
  class RuleProcessingTests {

    @Test
    @DisplayName("规则事件类型为空应抛出异常")
    void ruleWithBlankEventType_ShouldThrowException() {
      // Given
      Map<String, List<String>> invalidRules = Map.of(
        "", List.of("push") // 空事件类型
      );

      // When & Then
      assertThatThrownBy(() ->
        new EventDeliveryRuleConfiguration(List.of("email"), invalidRules))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("规则 #1: 事件类型不能为空");
    }

    @Test
    @DisplayName("规则渠道列表为空应抛出异常")
    void ruleWithEmptyChannels_ShouldThrowException() {
      // Given
      Map<String, List<String>> invalidRules = Map.of(
        "OrderCreated", Collections.emptyList()
      );

      // When & Then
      assertThatThrownBy(() ->
        new EventDeliveryRuleConfiguration(List.of("email"), invalidRules))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("规则 #1 (事件类型: OrderCreated): 渠道列表不能为空");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t"})
    @DisplayName("规则中渠道含空白值应抛出异常")
    void ruleWithBlankChannel_ShouldThrowException(String invalidChannel) {
      List<String> channels = new ArrayList<>();
      channels.add("push");
      channels.add(invalidChannel);

      // Given
      Map<String, List<String>> invalidRules = Map.of(
        "Login", channels
      );

      // When & Then
      assertThatThrownBy(() ->
        new EventDeliveryRuleConfiguration(List.of("email"), invalidRules))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("规则 #1 (事件类型: Login) 的渠道 #2: 渠道名称不能为空或空白");
    }

    @Test
    @DisplayName("渠道自动去重和修剪")
    void channelsShouldBeDeduplicatedAndTrimmed() {
      // Given
      Map<String, List<String>> rules = Map.of(
        "Login", List.of(" push ", "push", " SMS ")
      );

      // When
      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        List.of("email"),
        rules
      );

      // Then
      assertThat(config.getEventChannels())
        .containsEntry("Login", Set.of("push", "SMS"));
    }
  }

  @Nested
  @DisplayName("渠道查询测试")
  class ChannelLookupTests {

    @Test
    @DisplayName("存在事件规则时应返回规则渠道")
    void existingRule_ShouldReturnRuleChannels() {
      // Given
      Map<String, List<String>> rules = Map.of(
        "Login", List.of("push")
      );

      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        List.of("email"),
        rules
      );

      // When
      Set<String> channels = config.getChannelsForEvent("Login");

      // Then
      assertThat(channels).containsExactly("push");
    }

    @Test
    @DisplayName("无事件规则时应返回默认渠道")
    void nonExistingRule_ShouldReturnDefaultChannels() {
      // Given
      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        List.of("email", "sms"),
        Map.of("Login", List.of("push"))
      );

      // When
      Set<String> channels = config.getChannelsForEvent("UnknownEvent");

      // Then
      assertThat(channels).containsExactlyInAnyOrder("email", "sms");
    }

    @Test
    @DisplayName("空事件类型查询应抛出异常")
    void blankEventType_ShouldThrowException() {
      // Given
      EventDeliveryRuleConfiguration config = new EventDeliveryRuleConfiguration(
        List.of("email"),
        Collections.emptyMap()
      );

      // When & Then
      assertThatThrownBy(() -> config.getChannelsForEvent(" "))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("事件类型不能为空");
    }
  }
}
