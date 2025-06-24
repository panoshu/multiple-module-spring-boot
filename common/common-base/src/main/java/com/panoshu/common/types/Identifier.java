package com.panoshu.common.types;

import java.io.Serializable;

/**
 * 标记接口: 用于在泛型中约束类型必须是标识符, 标识所有领域实体的唯一身份标识符
 * 作为统一类型约束，用于框架层面对 ID 的通用处理
 *
 * @author <a href="mailto: me@panoshu.top">panoshu</a>
 * @version 1.0
 * @since 2025/6/1 12:07
 **/

public interface Identifier extends Serializable {
  String value();
}
