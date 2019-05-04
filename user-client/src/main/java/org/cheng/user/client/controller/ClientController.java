package org.cheng.user.client.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.cheng.user.client.feign.AdminFeignClient;
import org.cheng.user.client.feign.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

	@Autowired
	private UserFeignClient userFeignClient;

	@Autowired
	private AdminFeignClient adminFeignClient;

	@GetMapping(value = "/port/{id}")
	public String port(@PathVariable("id") Integer id, HttpServletResponse response) {
		Cookie cookie = new Cookie("test","111");
        cookie.setPath("/");
        cookie.setMaxAge(10000);
        response.addCookie(cookie);
		return userFeignClient.port(id);
	}
	
	@GetMapping(value = "/port2/{id}")
	public String port2(@PathVariable("id") Integer id, HttpServletResponse response) {
		Cookie cookie = new Cookie("test2","111");
        cookie.setPath("/");
        cookie.setMaxAge(10000);
        response.addCookie(cookie);
		return "";
	}

	@GetMapping(value = "/admin/{id}")
	public String admin(@PathVariable("id") Integer id) {
		return adminFeignClient.admin(id);
	}
}
