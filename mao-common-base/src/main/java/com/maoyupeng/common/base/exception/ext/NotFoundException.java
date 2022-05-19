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
public class NotFoundException extends JarvisException {
    public NotFoundException() {
        super(ResultErrorType.Common.NOT_FOUND);
    }
    public NotFoundException(String reasonPhrase) {
        super(ResultErrorType.Common.NOT_FOUND, reasonPhrase);
    }

    public NotFoundException(ResultErrorCodeI resultErrorCode) {
        super(resultErrorCode);
    }

    public NotFoundException(ResultErrorCodeI resultErrorCode, String message) {
        super(resultErrorCode, message);
    }
}
