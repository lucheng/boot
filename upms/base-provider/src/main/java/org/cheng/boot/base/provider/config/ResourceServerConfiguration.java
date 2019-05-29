package org.cheng.boot.base.provider.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器配置
 * 
 * @author Cheng
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.and()
		.authorizeRequests()
		// 只有超级管理员角色可执行远程端点
		.requestMatchers(EndpointRequest.toAnyEndpoint())
		.hasAnyAuthority("actuator")
		// 内部调用直接放行
		.antMatchers("/account/localLogin")
		.permitAll().anyRequest().authenticated()
		.and()
		.csrf().disable();
	}

}
