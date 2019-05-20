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
		
		StringBuilder s = new StringBuilder();
		s.append(" X-B3-TraceId:").append(request.getHeader("X-B3-TraceId"));
		s.append(" X-B3-SpanId:").append(request.getHeader("X-B3-SpanId"));
		s.append(" X-B3-ParentSpanId:").append(request.getHeader("X-B3-ParentSpanId"));
		s.append(" X-B3-Sampled:").append(request.getHeader("X-B3-Sampled"));
		s.append(" X-Span-Export:").append(request.getHeader("X-Span-Export"));
		s.append(" TraceId:").append(request.getHeader("TraceId"));
		s.append(" SpanId:").append(request.getHeader("SpanId"));
		s.append(" ParentSpanId:").append(request.getHeader("ParentSpanId"));
		s.append(" Sampled:").append(request.getHeader("Sampled"));
		
		LOG.info("user - " + "id:" + id + " prot:" + port + "  " + test + s);

		return "id:" + id + " prot:" + port + "  " + test + " " + s;
	}
}
