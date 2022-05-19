package com.maoyupeng.common.web.result.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.maoyupeng.common.base.util.EmptyUtil;
import com.maoyupeng.common.web.result.factory.PageAble;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

/**
 * 分页VO基础类
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/24 17:20
 */
@SuppressWarnings("unused")
@Data
@NoArgsConstructor
public class BasePageVO implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected PageVO pageInfo;
    @JsonIgnore
    protected PageAble pageAble;

    protected BasePageVO(PageAble pageAble) {
        this.pageAble = pageAble;
    }

    public PageVO getPageInfo() {
        if (!EmptyUtil.isEmpty(pageAble)) {
            return Optional.ofNullable(pageAble.getPageInfo()).orElse(defaultPageInfo(pageAble));
        }
        return null;
    }

    private PageVO defaultPageInfo(PageAble pageAble) {
        PageVO result = new PageVO();
        result.setPage(pageAble.getPage());
        result.setSize(pageAble.getSize());
        result.setHasNextPage(false);
        result.setHasPreviousPage(false);
        result.setTotal("0");

        return result;
    }
}
