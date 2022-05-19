package com.maoyupeng.common.web.config;

import com.maoyupeng.common.base.exception.JarvisException;
import com.maoyupeng.common.web.result.entity.ResultEntity;
import com.maoyupeng.common.web.result.factory.ResultError;
import com.maoyupeng.common.web.result.factory.ResultFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.Serializable;
import java.util.List;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 全局错误处理
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 07/05/2021 14:09
 */
@Slf4j
public class BaseAdviceConfig {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(Exception e) {
        log.error(e.getLocalizedMessage());
        return new ResultFactory<>().error().customMsg("Server exception.").customMsgDetail(e.getLocalizedMessage());
    }

    @ExceptionHandler(ResourceAccessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(ResourceAccessException e) {
        log.error("ResourceAccessException：" + e.getLocalizedMessage());
        return new ResultFactory<>().error().customMsg("Server exception.");
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(BindException e) {
        log.warn("BindException:" + e.getLocalizedMessage());
        ResultError<Serializable> error = new ResultFactory<>().error();
        ObjectError hibernateObjectError = getHibernateObjectError(e.getAllErrors());
        if (hibernateObjectError != null) {
            error.customMsg(hibernateObjectError.getDefaultMessage());
        }
        return error;
    }

    /**
     * RequestParam 参数为空，或者未传输异常（例如：Controller中定义的 @RequestParam 参数未传输或者空，则会抛出该异常）
     * @param e MissingServletRequestParameterException
     * @return ResultEntity<Serializable>
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(MissingServletRequestParameterException e) {
        log.error("MissingServletRequestParameterException:" + e.getLocalizedMessage());
        return new ResultFactory<>().error().customMsg("Missing request parameter.");
    }

    /**
     * 参数类型不匹配异常（例如：Long类型，传的是字符串"abc"，则会抛出该异常）
     * @param e MethodArgumentTypeMismatchException
     * @return ResultEntity<Serializable>
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(MethodArgumentTypeMismatchException e) {
        log.error("MethodArgumentTypeMismatchException:" + e.getLocalizedMessage());
        return new ResultFactory<>().error().customMsg("Argument type mismatch.").customMsgDetail("Please check your argument type.");
    }


    @ExceptionHandler(JarvisException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(JarvisException e) {
        log.error(e.getLocalizedMessage());
        return new ResultFactory<>().error().customCode(e.getErrorCode()).customMsg("Bad request.").customMsgDetail(e.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> exception(MethodArgumentNotValidException e) {
        log.warn(e.getLocalizedMessage());
        ResultError<Serializable> error = new ResultFactory<>().error().customMsg("Method argument not valid exception.");
        ObjectError hibernateObjectError = getHibernateObjectError(e.getBindingResult().getAllErrors());
        if (hibernateObjectError != null) {
            error.customMsgDetail(hibernateObjectError.getDefaultMessage());
        }
        return error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public ResultEntity<Serializable> illegalArgumentException(IllegalArgumentException e) {
        log.warn(e.getLocalizedMessage());
        return new ResultFactory<>().error().customMsg("Illegal argument exception").customMsgDetail(e.getLocalizedMessage());
    }

    private ObjectError getHibernateObjectError(List<ObjectError> allErrors) {
        if (!allErrors.isEmpty()) {
            return allErrors.get(0);
        }
        return null;
    }
}
