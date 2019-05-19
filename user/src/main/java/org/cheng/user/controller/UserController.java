package org.cheng.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class UserController {
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	@Value("${server.port}")
	Integer port;
	@GetMapping(value = "/port/{id}")
	public String port(@PathVariable("id") Integer id,@CookieValue("test")Object test, HttpServletRequest request){
		LOG.info("user - " + "id:" + id + " prot:" + port + "  " + test );
		return "id:" + id + " prot:" + port + "  " + test + request.getHeader("X-B3-TraceId");
	}
}
