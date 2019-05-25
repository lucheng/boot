package org.cheng.boot.auth.provider.service.impl;

import org.cheng.boot.auth.provider.service.feign.BaseUserAccountRemoteService;
import org.cheng.boot.common.core.model.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * Security用户信息获取实现类
 * 
 * @author Cheng
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private BaseUserAccountRemoteService baseUserAccountRemoteService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResultBody<Object> resultBody = baseUserAccountRemoteService.localLogin(username);
		log.info("loadUserByUsername",resultBody);
		if ("forezp".equals(username)) {
			return User.withUsername("forezp").password(passwordEncoder.encode("123456")).roles("USER").build();
		} else if ("admin".equals(username)) {
			return User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN").build();
		}
		return null;
	}

}
