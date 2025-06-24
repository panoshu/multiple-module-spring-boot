package com.panoshu.auth.user.types;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 20:16
 **/

public record PhoneNumber(String value) {
  private static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

  public PhoneNumber{
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("Phone number cannot be null or empty.");
    }

    if (value.matches(PHONE_REGEX)) {
      throw new IllegalArgumentException(String.format("%s is a phone number.", value));
    }
  }
}
