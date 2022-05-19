package com.maoyupeng.common.base.constant;

/**
 * 正则表达式常量
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/29 16:34
 */
@SuppressWarnings("unused")
public class RegexConstants {
    public class Md5 {
        /**
         * 常规MD5格式，字母兼容大小写
         */
        public static final String COMMON = "([a-f\\d]{32}|[A-F\\d]{32})";
        /**
         * 字母小写MD5格式
         */
        public static final String LOWER = "([a-f\\d]{32})";
        public RegexConstants and() {
            return RegexConstants.this;
        }
    }
}
