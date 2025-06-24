package com.panoshu.auth.domain.user.vo;

import com.panoshu.auth.user.types.Address;
import com.panoshu.auth.user.types.Gender;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * 用户个人资料值对象。
 * 作为值对象，它没有自己的身份，其相等性基于其属性值。
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 16:54
 **/

@Builder(toBuilder = true)
@Accessors(fluent = true)
public record UserProfile(
  String nickname,
  String avatarUrl,
  Gender gender,
  LocalDate dateOfBirth,
  Address address
) {
  public UserProfile {
    if (nickname != null && nickname.length() > 50) {
      throw new IllegalArgumentException("Nickname cannot exceed 50 characters.");
    }
  }

  /**
   * 创建一个默认的用户资料。
   * @return 默认的用户资料实例。
   */
  public static UserProfile defaultProfile() {
    return UserProfile.builder()
      .nickname("New User")
      .gender(Gender.UNKNOWN)
      .avatarUrl("default.png")
      .address(Address.defaultAddress())
      .build();
  }
}
