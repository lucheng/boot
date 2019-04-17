package org.cheng.admin.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class FooController {
//	@Value("${foo}")
//	String foo;
	@GetMapping(value = "/foo")
	public String hi(){
		return "00000";
	}
}
