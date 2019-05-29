package org.cheng.boot.base.provider.controller;

import org.cheng.boot.base.provider.entity.User;
import org.cheng.boot.common.core.model.ResultBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAccountController {
	@PostMapping("/account/localLogin")
	public ResultBody<Object> localLogin(@RequestParam(value = "username") String username) {
		User user = new User();
		user.setUserName(username);
		return ResultBody.success(user);
	}
}
