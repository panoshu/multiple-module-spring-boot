package com.panoshu.iam.user.types;

import lombok.Builder;
import lombok.experimental.Accessors;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 18:01
 **/

@Builder(toBuilder = true)
@Accessors(fluent = true)
public record Address(
  String province,
  String city,
  String street,
  String zipCode
) {
  public Address {
    if (city == null || city.isBlank()) {
      throw new IllegalArgumentException("城市不能为空");
    }
    if (zipCode.length() != 6) {
      throw new IllegalArgumentException("邮编长度必须为6位");
    }
  }

  public static Address defaultAddress() {
    return Address.builder()
      .province("UNK")
      .city("UNK")
      .street("UNK")
      .zipCode("000000")
      .build();
  }
}
