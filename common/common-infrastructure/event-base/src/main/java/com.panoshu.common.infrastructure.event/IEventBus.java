package com.panoshu.common.infrastructure.event;

import com.panoshu.common.domain.DomainEvent;
import com.panoshu.common.types.Identifier;

/**
 * description
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/22 22:29
 **/

public interface IEventBus {

  public void registerChannel(IEventChannel channel);
  public void dispatch(DomainEvent<Identifier> event);
}
