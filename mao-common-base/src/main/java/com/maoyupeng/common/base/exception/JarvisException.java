package com.maoyupeng.common.base.exception;


import lombok.extern.slf4j.Slf4j;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 07/05/2021 10:36
 */
@Slf4j
@SuppressWarnings("unused")
public class JarvisException extends Exception {
    private final int errorCode;
    private final String reasonPhrase;

    protected JarvisException(ResultErrorCodeI resultErrorCode) {
        super();

        this.errorCode = resultErrorCode.getErrorCode();
        this.reasonPhrase = resultErrorCode.getReasonPhrase();
    }

    protected JarvisException(ResultErrorCodeI resultErrorCode, String message) {
        super(message);

        this.errorCode = resultErrorCode.getErrorCode();
        this.reasonPhrase = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * 输出错误信息，并附带触发异常的类名
     * @param clazz 触发异常的类
     * @param <T> 泛型
     * @return JarvisException
     */
    public <T> JarvisException clazz(Class<T> clazz) {
        log.warn("{} <== {}", reasonPhrase, clazz.getName());
        return JarvisException.this;
    }

    /**
     * 自定义输入错误信息，并附带触发异常的类名
     * @param clazz 触发异常的类
     * @param msg 自定义错误信息
     * @param <T> 泛型
     * @return JarvisException
     */
    public <T> JarvisException clazz(Class<T> clazz, String msg) {
        log.warn("{} <== {}", msg, clazz.getName());
        return JarvisException.this;
    }
}
