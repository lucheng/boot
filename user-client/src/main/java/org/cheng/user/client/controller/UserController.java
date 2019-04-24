package org.cheng.user.client.controller;

import org.cheng.user.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "get")
	public String get(Integer id) {
		return userService.get(id);
	}
	
	@GetMapping(value = "del")
	public String del(Integer id) {
		return userService.del(id);
	}
}
