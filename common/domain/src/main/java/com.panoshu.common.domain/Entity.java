package com.panoshu.common.domain;

import com.panoshu.common.types.Identifier;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

/**
 * 实体基类
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 14:15
 **/

@Getter
public abstract class Entity<ID extends Identifier> {
  private final ID id;
  private final Instant createdAt; // 创建时间
  private Instant updatedAt; // 更新时间
  private long version;      // 乐观锁版本号

  protected Entity(ID id) {
    if (id == null) {
      throw new IllegalArgumentException("Entity ID cannot be null.");
    }
    this.id = id;
    this.createdAt = Instant.now(); // 默认创建时间
    this.updatedAt = Instant.now(); // 默认更新时间
    this.version = 0;               // 初始版本号
  }

  // 通常在更新操作时调用，更新updatedAt并增加版本
  protected void markUpdated() {
    this.updatedAt = Instant.now();
    this.version++;
  }

  // 实体不变性校验方法 (抽象方法，强制子类实现)
  protected abstract void validateInvariants();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    return o instanceof Entity<?> that && Objects.equals(this.id, that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
