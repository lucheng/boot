package org.cheng.user.client.controller;

import java.util.HashMap;
import java.util.Map;

import org.cheng.user.client.entity.User;
import org.cheng.user.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/")
@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private CacheManager cacheManager;
	
	@GetMapping(value = "get")
	public User get(Integer id) {
		return userService.get(id);
	}
	
	@GetMapping(value = "save")
	public User save(Integer id) {
		User user = new User();
		user.setId(id);
		return userService.save(user);
	}
	
	@GetMapping(value = "del")
	public String del(Integer id) {
		return userService.del(id);
	}
	
	@GetMapping(value = "removeAll")
	public void removeAll() {
		userService.removeAll();
	}
	
	@GetMapping(value = "cache")
	public Map<String,Cache> cache() {
		Map<String,Cache> map = new HashMap<>();
		for(String name:cacheManager.getCacheNames()) {
			map.put(name, cacheManager.getCache(name));
		}
		return map;
	}
}
