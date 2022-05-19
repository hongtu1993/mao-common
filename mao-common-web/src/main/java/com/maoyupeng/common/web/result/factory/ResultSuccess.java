package com.maoyupeng.common.web.result.factory;

import com.maoyupeng.common.base.exception.ResultErrorCodeI;
import com.maoyupeng.common.base.exception.ResultErrorType;
import com.maoyupeng.common.web.result.entity.ResultEntity;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 成功的响应对象模型
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:55
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public final class ResultSuccess<T extends Serializable> extends ResultEntity<T> {
    private static final boolean DEFAULT_SUCCESS = true;
    private static final ResultErrorCodeI DEFAULT_ERROR_TYPE = ResultErrorType.System.SUCCESS;

    public ResultSuccess() {
        init();
    }

    public ResultSuccess(T data) {
        super.setData(data);
        init();
    }

    private void init() {
        super.setMsg(DEFAULT_ERROR_TYPE.getReasonPhrase());
        super.setCode(DEFAULT_ERROR_TYPE.getErrorCode());
        super.setSuccess(DEFAULT_SUCCESS);
    }

    public ResultEntity<T> customMsg(String msg) {
        super.setMsg(msg);
        return this;
    }

    @Override
    public void setSuccess(Boolean flag) {
        // 强制为true， 不能修改
        super.setSuccess(DEFAULT_SUCCESS);
    }
}
