package com.maoyupeng.common.base.exception.ext;

import com.maoyupeng.common.base.exception.JarvisException;
import com.maoyupeng.common.base.exception.ResultErrorCodeI;
import com.maoyupeng.common.base.exception.ResultErrorType;

/**
 * 业务异常（已存在检查异常）
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/28 12:28
 */
public class ExistException extends JarvisException {
    public ExistException() {
        super(ResultErrorType.Common.ALREADY_EXIST);
    }
    public ExistException(String reasonPhrase) {
        super(ResultErrorType.Common.ALREADY_EXIST, reasonPhrase);
    }

    public ExistException(ResultErrorCodeI resultErrorCode) {
        super(resultErrorCode);
    }

    public ExistException(ResultErrorCodeI resultErrorCode, String message) {
        super(resultErrorCode, message);
    }
}
