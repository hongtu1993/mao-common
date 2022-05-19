package com.maoyupeng.common.web.result.factory;

import com.maoyupeng.common.web.result.entity.PageVO;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页自定义注入对象
 *
 * @author 毛宇鹏
 * @copyright Copyright (c) 2022  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/22 21:13
 */
@Data
public class PageAble implements Serializable {
    private int page;
    private int size;

    private PageVO pageInfo;
}
