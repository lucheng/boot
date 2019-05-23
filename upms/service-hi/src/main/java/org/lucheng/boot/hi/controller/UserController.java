package org.lucheng.boot.hi.controller;

import org.lucheng.boot.hi.entity.security.User;
import org.lucheng.boot.hi.service.security.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserServiceImpl userService;

	@RequestMapping(value = "/registry", method = RequestMethod.POST)
	public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		return userService.create(username, password);
	}

}
