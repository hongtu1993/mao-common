package com.maoyupeng.common.base.util;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 对象映射工具
 *
 * 1. mapList: 将集合拷贝成List
 * 2. mapObject: 将对象拷贝成对象
 *
 * @author mao
 * @date 13/07/2020 15:50
 */
@SuppressWarnings("unused")
public final class MapperUtil extends ModelMapper {
    private static final ModelMapper MODEL_MAPPER;

    private MapperUtil() {
        throw new IllegalStateException("Utility class");
    }

    static {
        MODEL_MAPPER = new ModelMapper();
        MODEL_MAPPER.getConfiguration()
                .setPropertyCondition(Conditions.isNotNull())
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    /**
     * 获取 ModelMapper 实例
     * @return ModelMapper
     */
    public static ModelMapper getInstance() {
        return MODEL_MAPPER;
    }

    /**
     * 映射List
     * @param source List<S>
     * @param targetClass Class<T>
     * @param <S> S
     * @param <T> T
     * @return List<T>
     */
    public static <S, T> List<T> mapList(Collection<S> source, Class<T> targetClass) {
        if (source == null) {
            return new ArrayList<>();
        }
        return source.stream().map(element -> getInstance().map(element, targetClass)).collect(Collectors.toList());
    }

    public static <S, T> T mapObject(S source, Class<T> targetClass) {
        if (EmptyUtil.isEmpty(source)) {
            return null;
        }
        return getInstance().map(source, targetClass);
    }
}
