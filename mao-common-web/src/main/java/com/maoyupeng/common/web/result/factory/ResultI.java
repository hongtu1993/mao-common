package com.maoyupeng.common.web.result.factory;


import java.io.Serializable;
import java.util.List;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description 响应对象 接口定义
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 12/04/2021 10:32
 */
@SuppressWarnings("unused")
public interface ResultI<T extends Serializable> {

    /**
     * 请求失败模型
     * @return ResultError<T>
     */
    ResultError<T> error();

    /**
     * 请求成功模型
     * @return ResultSuccess<T>
     */
    ResultSuccess<T> success();

    /**
     * 请求成功模型 (带data参数)
     * @param data 响应参数
     * @return ResultSuccess<T>
     */
    ResultSuccess<T> success(T data);

    /**
     * 返回数组模型（不含分页参数）
     * @param data 响应参数（传空则会默认返回空数组）
     * @return ResultCollection<T>
     */
    ResultCollection<T> collection(List<T> data);
}
