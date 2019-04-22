package org.cheng.user.client.controller;

import org.cheng.user.client.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	@Autowired
	private UserFeignClient userFeignClient;

	@GetMapping(value = "/port/{id}")
	public String port(@PathVariable("id") Integer id) {
		return userFeignClient.port(id);
	}
}
