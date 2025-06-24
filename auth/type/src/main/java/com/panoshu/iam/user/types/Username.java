package com.panoshu.iam.user.types;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 18:45
 **/

public record Username(String value) {
  public Username {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("User name cannot be null or empty.");
    }

    if (value.length() < 4 || value.length() > 20){
      throw new IllegalArgumentException(
        String.format("User name length must be between 4 and 20 characters. but now it is %s", value)
      );
    }
  }
}
