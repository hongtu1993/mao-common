package com.maoyupeng.common.web.resolver;

import com.maoyupeng.common.base.exception.ext.ParamException;
import com.maoyupeng.common.base.util.EmptyUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 自动解析请求参数并注入到 PageAble 对象，在controller中可直接使用
 *
 * 激活此功能还需要在 SecurityConfig中添加 addArgumentResolvers
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/22 21:11
 */
public class PageObjectMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().isAssignableFrom(PageAble.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String page = nativeWebRequest.getParameter("page");
        String size = nativeWebRequest.getParameter("size");

        // 如果分页参数为空时，视为不进行分页查询，相关查询业务需要判断 分页对象是否不为空，才进行分页查询
        if (EmptyUtil.EMPTY_STRING.equalsIgnoreCase(EmptyUtil.manageString(page, EmptyUtil.EMPTY_STRING))) {
            return null;
        }

        Integer pageIndex = EmptyUtil.manageInt(page, null);
        if (pageIndex == null) {
            return null;
        }

        Integer pageSize = EmptyUtil.manageInt(size, 20);

        int maxPageSize = 200;
        if (pageSize > maxPageSize) {
            throw new ParamException("Max page size cannot greater than " + maxPageSize);
        }

        PageAble result = new PageAble();
        result.setPage(pageIndex);
        result.setSize(pageSize);

        return result;
    }
}
