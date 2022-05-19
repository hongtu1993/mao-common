package com.maoyupeng.common.web.result.factory;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 响应对象模型(基础)
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:33
 */
@Data
public class ResultBase implements Serializable {
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msgDetail;
    private Integer code;
    private Boolean success;
}
