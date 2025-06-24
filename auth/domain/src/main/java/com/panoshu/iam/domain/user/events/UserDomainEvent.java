package com.panoshu.iam.domain.user.events;

import com.panoshu.common.domain.DomainEvent;
import com.panoshu.common.types.UlidIdentifier;

/**
 * User领域的密封事件接口。
 * 只有在 'permits' 子句中列出的产品事件才能实现此接口。
 * 这确保了产品事件的内聚性，并允许在处理产品事件时进行详尽性检查。
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 21:48
 **/

public sealed interface UserDomainEvent extends DomainEvent<UlidIdentifier>
  permits UserRegisteredEvent {

}
