package com.panoshu.common.infrastructure.event;

import com.panoshu.common.domain.DomainEvent;
import com.panoshu.common.types.Identifier;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/15 19:20
 **/

public interface IEventChannel {

  void dispatch(DomainEvent<Identifier> event);

  default String getChannelName() {
    String simpleName = this.getClass().getSimpleName();
    return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
  }
}
