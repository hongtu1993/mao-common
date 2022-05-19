package com.maoyupeng.common.web.result.factory;


import com.maoyupeng.common.base.exception.ResultErrorCodeI;
import com.maoyupeng.common.base.exception.ResultErrorType;
import com.maoyupeng.common.web.result.entity.ResultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 响应对象实现类
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 11:46
 */
public final class ResultFactory<T extends Serializable> implements ResultI<T> {
    /**
     * 请求失败模型
     * @return ResultError<T>
     */
    @Override
    public ResultError<T> error() {
        return new ResultError<>();
    }

    /**
     * 请求成功模型
     * @return ResultSuccess<T>
     */
    @Override
    public ResultSuccess<T> success() {
        return new ResultSuccess<>();
    }

    /**
     * 请求成功模型 (带data参数)
     * @param data 响应参数
     * @return ResultSuccess<T>
     */
    @Override
    public ResultSuccess<T> success(T data) {
        return new ResultSuccess<>(data);
    }

    /**
     * 返回数组模型（不含分页参数）
     *
     * @param data 响应参数（传空则会默认返回空数组）
     * @return ResultCollection<T>
     */
    @Override
    public ResultCollection<T> collection(List<T> data) {
        return new ResultCollection<>(data);
    }

    @SuppressWarnings("unused")
    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class ResultSuccessTwo<T extends Serializable> extends ResultEntity<T> implements Serializable {
        private static final boolean DEFAULT_SUCCESS = true;

        private ResultSuccessTwo() {
            init();
        }

        private ResultSuccessTwo(T data) {
            super.setData(data);
            init();
        }

        private void init() {
            ResultErrorCodeI defaultErrorType = ResultErrorType.System.SUCCESS;
            super.setMsg(defaultErrorType.getReasonPhrase());
            super.setCode(defaultErrorType.getErrorCode());
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
}
