package org.cheng.user.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserController {
	@Value("${server.port}")
	Integer port;
	@GetMapping(value = "/port/{id}")
	public String port(@PathVariable("id") Integer id,@CookieValue("test")Object test){
		return "id:" + id + " prot:" + port + "  " + test ;
	}
}
