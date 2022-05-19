package com.maoyupeng.common.web.result.entity;

import com.maoyupeng.common.web.result.factory.ResultBase;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 响应对象模型
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ResultEntity<T extends Serializable> extends ResultBase {
    private T data;
}
