package com.maoyupeng.common.web.resolver;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 毛宇鹏
 * @version 1.0
 * @description
 * @copyright Copyright (c) 2020  ALL RIGHTS RESERVED.
 * @company Jarvis
 * @date 2022/4/23 7:14 PM
 */
@Data
public class CurrentUser implements Serializable {
	private Long userId;
	private String username;
	private String headAvatar;
	private String nickName;
	private String email;
}