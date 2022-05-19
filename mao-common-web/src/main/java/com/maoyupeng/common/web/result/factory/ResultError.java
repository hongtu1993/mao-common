package com.maoyupeng.common.web.result.factory;

import com.maoyupeng.common.base.exception.ResultErrorCodeI;
import com.maoyupeng.common.base.exception.ResultErrorType;
import com.maoyupeng.common.web.result.entity.ResultEntity;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 失败的响应对象模型
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:55
 */
@ToString
@SuppressWarnings("unused")
public final class ResultError<T extends Serializable> extends ResultEntity<T> {
    private static final boolean DEFAULT_FAIL = false;

    public ResultError() {
        super.setSuccess(DEFAULT_FAIL);
        this.customCode(ResultErrorType.System.BAD_REQUEST);
    }

    public ResultError<T> customMsg(String msg) {
        super.setMsg(msg);
        return this;
    }

    public ResultError<T> customMsgDetail(String msg) {
        super.setMsgDetail(msg);
        return this;
    }

    public ResultError<T> customCode(int code) {
        super.setCode(code);
        return this;
    }

    public ResultError<T> customCode(ResultErrorCodeI errorCode) {
        super.setCode(errorCode.getErrorCode());
        super.setMsg(errorCode.getReasonPhrase());
        return this;
    }

    @Override
    public void setSuccess(Boolean flag) {
        // 强制为false， 不能修改
        super.setSuccess(DEFAULT_FAIL);
    }
}
