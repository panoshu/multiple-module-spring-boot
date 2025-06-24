package com.panoshu.common.types;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;

import java.time.Instant;

/**
 * ULID Identifier
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/5/31 22:52
 **/

public record UlidIdentifier(String value) implements Identifier {

  public UlidIdentifier {
    if (value == null || value.isBlank()) {
      throw new IllegalArgumentException("ULID identifier value cannot be null or empty.");
    }

    if (!Ulid.isValid(value)) {
      throw new IllegalArgumentException("Invalid ULID: " + value);
    }
  }

  // 静态工厂方法：生成新的 ULID
  public static UlidIdentifier generate() {
    return new UlidIdentifier(UlidCreator.getUlid().toString());
  }

  // 静态工厂方法：从字符串创建 ULID
  public static UlidIdentifier of(String value) {
    return new UlidIdentifier(value);
  }

  public Instant getInstant() {
    return Ulid.getInstant(value);
  }
}
