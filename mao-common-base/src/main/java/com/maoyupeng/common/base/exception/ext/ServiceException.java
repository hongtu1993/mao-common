package com.maoyupeng.common.base.exception.ext;

import com.maoyupeng.common.base.exception.JarvisException;
import com.maoyupeng.common.base.exception.ResultErrorType;

/**
 * 服务异常
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/22 19:52
 */
public class ServiceException extends JarvisException {
    public ServiceException(String reasonPhrase) {
        super(ResultErrorType.Common.SERVICE_FAIL, reasonPhrase);
    }
}
