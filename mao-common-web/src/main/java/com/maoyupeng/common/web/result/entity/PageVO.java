package com.maoyupeng.common.web.result.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 20/01/2022 16:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageVO implements Serializable {
    private Integer page;
    private Integer size;
    private String total;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
}
