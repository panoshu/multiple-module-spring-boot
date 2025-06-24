package com.panoshu.auth.domain.user.entity;

import com.panoshu.auth.domain.user.vo.UserProfile;
import com.panoshu.auth.user.types.Email;
import com.panoshu.auth.user.types.PhoneNumber;
import com.panoshu.auth.user.types.UserStatus;
import com.panoshu.auth.user.types.Username;
import com.panoshu.common.domain.AggregateRoot;
import com.panoshu.common.types.UlidIdentifier;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Objects;

/**
 * 用户聚合根
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 18:28
 **/

@Getter
@Accessors(fluent = true)
public class UserAggregateRoot extends AggregateRoot<UlidIdentifier> {
  private Username username; // 用户名，唯一
  private Email email; // 邮箱，唯一
  private PhoneNumber phoneNumber; // 手机号，唯一
  private UserStatus status;
  private Instant registrationDate;
  private Instant lastLoginDate;
  private Long version; // 乐观锁版本号
  private UserProfile profile;

  // 私有构造函数，强制通过工厂方法或 load 方法创建
  private UserAggregateRoot(
    UlidIdentifier id,
    Username username, Email email, PhoneNumber phoneNumber, UserStatus status,
    Instant registrationDate, Instant lastLoginDate, Long version,
    UserProfile profile
  ) {
    super(id);
    this.username = username;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.status = status;
    this.registrationDate = registrationDate;
    this.lastLoginDate = lastLoginDate;
    this.version = version;
    this.profile = profile != null ? profile : UserProfile.defaultProfile();
    validateInvariants();
  }

  /**
   * 工厂方法：用于创建新用户（例如通过注册流程）。
   * @param id 用户ID
   * @param username 用户名
   * @param email 邮箱
   * @param phoneNumber 手机号
   * @return 新创建的用户实例
   */
  public static UserAggregateRoot create(UlidIdentifier id, Username username, Email email, PhoneNumber phoneNumber) {
    return new UserAggregateRoot(
      id, username, email, phoneNumber,
      UserStatus.ACTIVE, Instant.now(), null, 0L,
      UserProfile.defaultProfile());
  }

  /**
   * 工厂方法：用于从持久化层加载用户。
   * @param id 用户ID
   * @param name 用户名
   * @param email 邮箱
   * @param phoneNumber 手机号
   * @param status 用户状态
   * @param registrationDate 注册日期
   * @param lastLoginDate 最后登录日期
   * @param version 版本号
   * @param profile 用户资料
   * @return 加载的用户实例
   */
  private static UserAggregateRoot load(
    String id, String name, String email, String phoneNumber, UserStatus status,
    Instant registrationDate, Instant lastLoginDate, Long version,
    UserProfile profile
  ) {
    UlidIdentifier userId = UlidIdentifier.of(id);
    Username userName = new Username(name);
    Email userEmail = email != null ? new Email(email) : null;
    PhoneNumber userPhoneNumber = phoneNumber != null ? new PhoneNumber(phoneNumber) : null;

    return new UserAggregateRoot(userId, userName, userEmail, userPhoneNumber, status, registrationDate, lastLoginDate, version, profile);
  }

  /**
   * 更新用户资料。
   * @param newProfile 新的用户资料值对象。
   */
  public void updateProfile(UserProfile newProfile) {
    Objects.requireNonNull(newProfile, "UserProfile cannot be null.");
    this.profile = newProfile;
    // TODO: 发布 UserProfileUpdatedEvent 领域事件
  }

  /**
   * 改变用户状态。
   * @param newStatus 新的用户状态。
   */
  public void changeStatus(UserStatus newStatus) {
    Objects.requireNonNull(newStatus, "User status cannot be null.");
    if (this.status == newStatus) {
      return; // 状态未改变
    }
    // TODO: 添加状态转换的业务规则，例如不能从 DELETED 转换为 ACTIVE
    this.status = newStatus;
    // TODO: 发布 UserStatusChangedEvent 领域事件
  }

  /**
   * 软删除用户。
   */
  public void markAsDeleted() {
    if (this.status == UserStatus.DELETED) {
      return;
    }
    this.status = UserStatus.DELETED;
    // TODO: 发布 UserDeletedEvent 领域事件
  }

  /**
   * 判断用户是否被锁定。
   * @return 如果用户状态为 LOCKED 则返回 true。
   */
  public boolean isLocked() {
    return this.status == UserStatus.LOCKED;
  }

  /**
   * 领域不变性校验。
   * 在对象创建和状态改变时调用，确保对象始终处于有效状态。
   */
  protected void validateInvariants() {
    //TODO: 这里只校验聚合根层面的业务规则
  }
}
