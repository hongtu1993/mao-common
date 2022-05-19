package com.maoyupeng.common.base.util;

/**
 * 分布式ID生成工具类
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/20 11:22
 */
@SuppressWarnings("unused")
public final class IdUtil {
    private IdUtil() {
        throw new IllegalStateException("Utility class");
    }

    private static final IdBuilder WORKER = new IdBuilder(1, 1, 100);

    public static Long getRandId() {
        return WORKER.nextId();
    }

    public static String getRandIdString() {
        return String.valueOf(WORKER.nextId());
    }
}
