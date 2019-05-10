package org.cheng.user.controller;

import org.cheng.user.feign.UserClientFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserClientController {
	
	@Autowired
	private UserClientFeignClient userClientFeignClient;
	
	@GetMapping(value = "/user/get")
	public Object get(Integer id){
		return userClientFeignClient.get(id);
	}
}
