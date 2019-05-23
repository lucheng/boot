package org.cheng.boot.auth.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Security用户信息获取实现类
 * 
 * @author Cheng
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if ("forezp".equals(username)) {
			return User.withUsername("forezp").password(passwordEncoder.encode("123456")).roles("USER").build();
		} else if ("admin".equals(username)) {
			return User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN").build();
		}
		return null;
	}

}
