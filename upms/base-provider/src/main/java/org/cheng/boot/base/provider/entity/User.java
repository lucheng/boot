package org.cheng.boot.base.provider.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "base_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private String userName;
	private String nickName;
	private String userType;
	private String email;
	private Integer status;
}