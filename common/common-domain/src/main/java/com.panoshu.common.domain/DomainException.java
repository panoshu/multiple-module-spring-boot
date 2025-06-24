package com.panoshu.common.domain;

import com.panoshu.common.exception.ErrorCode;
import lombok.Getter;

/**
 * 领域层异常基类
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 11:58
 **/

public abstract class DomainException extends RuntimeException {
  @Getter
  private final ErrorCode errorCode;
  private final Object[] messageArgs; // 用于格式化错误信息

  public DomainException(ErrorCode errorCode, String message, Object... messageArgs) {
    super(message);
    this.errorCode = errorCode;
    this.messageArgs = messageArgs;
  }

  public DomainException(ErrorCode errorCode, String message, Throwable cause, Object... messageArgs) {
    super(message, cause);
    this.errorCode = errorCode;
    this.messageArgs = messageArgs;
  }

  // 提供一个获取格式化错误信息的方法
  @Override
  public String getMessage() {
    if (messageArgs != null && messageArgs.length > 0) {
      return String.format(super.getMessage(), messageArgs);
    }
    return super.getMessage();
  }
}
