package com.panoshu.commom.types;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.panoshu.common.types.UlidIdentifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/25 06:57
 **/

public class UlidIdentifierTest {
  @Test
  @DisplayName("Should create UlidIdentifier with a valid ULID string using AssertJ")
  void constructor_withValidUlid_shouldCreateInstance_assertJ() {
    String validUlid = UlidCreator.getUlid().toString();
    UlidIdentifier ulidIdentifier = new UlidIdentifier(validUlid);

    assertThat(ulidIdentifier).isNotNull();
    assertThat(ulidIdentifier.value()).isEqualTo(validUlid);
  }

  @Test
  @DisplayName("Should throw IllegalArgumentException for null ULID string using AssertJ")
  void constructor_withNullUlid_shouldThrowException_assertJ() {
    // AssertJ 捕获异常的用法
    assertThatThrownBy(() -> new UlidIdentifier(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage("ULID identifier value cannot be null or empty.");
  }

  @Test
  @DisplayName("generate() should return a new, valid UlidIdentifier using AssertJ")
  void generate_shouldReturnValidUlidIdentifier_assertJ() {
    UlidIdentifier generatedUlid = UlidIdentifier.generate();
    assertThat(generatedUlid)
      .isNotNull()
      .extracting(UlidIdentifier::value) // 提取 record 的 value 字段
      .isNotNull()
      .satisfies(ulidString -> assertThat(Ulid.isValid(ulidString)).isTrue()); // 进一步断言 ULID 字符串的有效性
  }

  @Test
  @DisplayName("getInstant() should return the correct Instant from the ULID using AssertJ")
  void getInstant_shouldReturnCorrectInstant_assertJ() {
    String ulidString = UlidCreator.getUlid().toString();
    UlidIdentifier ulidIdentifier = new UlidIdentifier(ulidString);

    Instant expectedInstant = Ulid.getInstant(ulidString);
    Instant actualInstant = ulidIdentifier.getInstant();

    assertThat(actualInstant).isEqualTo(expectedInstant);
  }
}
