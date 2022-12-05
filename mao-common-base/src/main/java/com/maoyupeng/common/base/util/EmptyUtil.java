package com.maoyupeng.common.base.util;


import com.maoyupeng.common.base.exception.ext.ParamException;

import java.util.List;

/**
 * 非空判断工具类
 *
 * 1. isEmpty:          判断对象是否为空对象
 * 2. manageString:     判断字符串是否为空且可指定默认返回值
 * 3. manageInt:        将对象转换为Integer类型且可指定默认返回值
 * 4. isEmptyList:      判断数组是否为空
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/20 09:44
 */
@SuppressWarnings("unused")
public final class EmptyUtil {
    public static final String EMPTY_STRING = "";
    private static final String NULL_STRING = "null";

    private EmptyUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 判断对象是否为空对象
     *
     * @param obj 对象target
     * @return true / false
     */
    public static boolean isEmpty(Object obj) {
        if (obj != null) {
            return "".equals(obj.toString().trim());
        } else {
            return true;
        }
    }

    /**
     * 判断目标字符串对象是否为空, 如果不为, 则返回字符串, 否则, 返回自定义字符串
     *
     * @param obj 目标字符串对象
     * @param str 自定义字符串
     * @return 字符串
     */
    public static String manageString(Object obj, String str) {
        if (!isEmpty(obj)) {
            String val = obj.toString().trim();
            if (!NULL_STRING.equalsIgnoreCase(val) && !NULL_STRING.toUpperCase().equalsIgnoreCase(val) && !EMPTY_STRING.equalsIgnoreCase(val)) {
                return val;
            } else {
                return str;
            }
        } else {
            return str;
        }
    }

    public static void noEmpty(String obj, String name) {
        if (!isEmpty(obj)) {
            String val = obj.trim();
            if (EMPTY_STRING.equalsIgnoreCase(val)) {
                throw new IllegalArgumentException("[Assertion failed] - this String argument " + name + " must not empty");
            }
        }
        throw new NullPointerException("[Assertion failed] - this String argument " + name + " must not null");
    }

    /**
     * 转换为int<br>
     * 如果给定的值为空，或者转换失败，返回默认值<br>
     * 转换失败不会报错
     *
     * @param value        被转换的值
     * @param defaultValue 转换错误时的默认值
     * @return 结果
     */
    public static Integer manageInt(Object value, Integer defaultValue) {
        if (value == null) {
            return defaultValue;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof Number) {
            return ((Number) value).intValue();
        }
        final String valueStr = manageString(value, null);
        if (valueStr == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(valueStr.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 判断对象是否为空集合
     * @param collection 要判断的集合
     * @return true/false
     */
    public static <T> boolean isEmptyList(List<T> collection) {
        return (isEmpty(collection) || collection.isEmpty());
    }

    /**
     * 判断集合对象是否为空集合，空集合则抛出异常
     * @param collection 待验证的集合
     * @param errorMessage 自定义错误参数
     * @param <T> 泛型类型
     * @return 如果校验通过，返回元数据
     * @throws ParamException 空集合则抛出异常
     */
    public static <T> List<T> isEmptyListElseThrow(List<T> collection, String errorMessage) throws ParamException {
        if (isEmptyList(collection)) {
            throw new ParamException(errorMessage);
        } else {
            return collection;
        }
    }

    public static void noNull(Object object, String name) {
        if (object == null) {
            throw new NullPointerException("[Assertion failed] - " + name + "must not null");
        }
    }
}
