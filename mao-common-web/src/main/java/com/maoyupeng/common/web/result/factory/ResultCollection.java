package com.maoyupeng.common.web.result.factory;

import com.maoyupeng.common.base.exception.ResultErrorCodeI;
import com.maoyupeng.common.base.exception.ResultErrorType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public final class ResultCollection<T extends Serializable> extends ResultBase {
    private static final boolean DEFAULT_SUCCESS = true;
    private static final ResultErrorCodeI DEFAULT_ERROR_TYPE = ResultErrorType.System.SUCCESS;

    private Collection<T> data;

    public ResultCollection(Collection<T> data) {
        if (data == null || data.isEmpty()) {
            this.data = new ArrayList<>();
        } else {
            this.data = data;
        }

        super.setSuccess(true);
        super.setCode(DEFAULT_ERROR_TYPE.getErrorCode());
        super.setMsg(DEFAULT_ERROR_TYPE.getReasonPhrase());
    }

    @Override
    public void setSuccess(Boolean flag) {
        // 强制为true， 不能修改
        super.setSuccess(DEFAULT_SUCCESS);
    }

    @Override
    public void setCode(Integer errorCode) {
        super.setCode(DEFAULT_ERROR_TYPE.getErrorCode());
    }
}
