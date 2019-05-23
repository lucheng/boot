package org.cheng.boot.base.provider.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName(value = "base_user")
public class User {
	private Long id;
	private String name;
	private Integer age;
	private String email;
}