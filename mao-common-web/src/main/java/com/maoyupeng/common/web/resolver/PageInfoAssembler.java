package com.maoyupeng.common.web.resolver;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.maoyupeng.common.web.result.entity.PageVO;

/**
 * 分页参数装配
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/22 22:15
 */
public class PageInfoAssembler {
    public <T> PageVO toPageVO(Page<T> pageResult) {
        // 封装响应分页参数
        if (pageResult != null) {
            PageInfo<T> pageInfo = pageResult.toPageInfo();

            return PageVO.builder()
                    .page(pageInfo.getPageNum())
                    .size(pageInfo.getPageSize())
                    .total(String.valueOf(pageInfo.getTotal()))
                    .hasNextPage(pageInfo.isHasNextPage())
                    .hasPreviousPage(pageInfo.isHasPreviousPage())
                    .build();
        }

        return null;
    }
}
