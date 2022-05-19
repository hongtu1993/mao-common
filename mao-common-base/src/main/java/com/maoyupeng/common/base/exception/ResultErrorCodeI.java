package com.maoyupeng.common.base.exception;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:35
 */
public interface ResultErrorCodeI {
    /**
     * 错误码
     * @return int
     */
    int getErrorCode();

    /**
     * 描述信息
     * @return String
     */
    String getReasonPhrase();
}
