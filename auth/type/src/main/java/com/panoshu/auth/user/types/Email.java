package com.panoshu.auth.user.types;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 19:33
 **/

public record Email(String value) {
  private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

  public Email{
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("User email cannot be null or empty.");
    }

    if (value.matches(EMAIL_REGEX)) {
      throw new IllegalArgumentException("User email contains illegal characters.");
    }
  }

}
