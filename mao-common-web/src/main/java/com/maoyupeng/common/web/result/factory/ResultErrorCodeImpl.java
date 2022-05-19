package com.maoyupeng.common.web.result.factory;


import com.maoyupeng.common.base.exception.ResultErrorCodeI;
import lombok.ToString;

/**
 * @author mao
 */
@SuppressWarnings("unused")
@ToString
public final class ResultErrorCodeImpl implements ResultErrorCodeI {
    private final int errorCode;
    private final String reasonPhrase;

    public ResultErrorCodeImpl() {
        this.errorCode = 200;
        this.reasonPhrase = "Success.";
    }
    public ResultErrorCodeImpl(int errorCode, String reasonPhrase) {
        this.errorCode = errorCode;
        this.reasonPhrase = reasonPhrase;
    }
    /**
     * 错误码
     *
     * @return int
     */
    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    /**
     * 描述信息
     *
     * @return String
     */
    @Override
    public String getReasonPhrase() {
        return this.reasonPhrase;
    }
}
