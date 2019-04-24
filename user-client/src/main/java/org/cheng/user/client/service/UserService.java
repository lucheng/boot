package org.cheng.user.client.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Cacheable(value = "user")
	public String get(Integer id) {
		System.out.println("-----");
		return String.valueOf(id);
	}
	
	@CacheEvict(value = "user")
	public String del(Integer id) {
		System.out.println("-----");
		return String.valueOf(id);
	}
	
}
