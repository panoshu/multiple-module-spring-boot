package com.panoshu.iam.domain.user.events;

import com.panoshu.common.types.UlidIdentifier;
import com.panoshu.iam.user.types.Email;
import com.panoshu.iam.user.types.PhoneNumber;
import com.panoshu.iam.user.types.Username;

import java.time.Instant;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 18:36
 **/

public record UserRegisteredEvent(
  UlidIdentifier UserId,
  Username username,
  Email email,
  PhoneNumber phoneNumber,
  UlidIdentifier eventId,
  Instant occurredOn) implements UserDomainEvent {
}
