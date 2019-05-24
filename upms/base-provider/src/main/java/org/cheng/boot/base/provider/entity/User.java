package org.cheng.boot.base.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "base_user")
public class User {
	private Long userId;
	private String userName;
	private String nickName;
	private String userType;
	private String email;
	private Integer status;
}