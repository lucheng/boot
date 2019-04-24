package org.cheng.user.client.service;

import java.util.HashMap;
import java.util.Map;

import org.cheng.user.client.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 统一命名缓存 名称
 * @author Cheng
 */
@CacheConfig(cacheNames = { "user" })
@Service
public class UserService {
	
	@Autowired
	private CacheManager cacheManager;
	
	/**
	 * 单个命名缓存名称
	 * sync 默认为false 为true 需要同步等待
	 * @param id
	 * @return
	 */
	@Cacheable(cacheNames = "user", key = "#id",sync = false)
	public User get(Integer id) {
		System.out.println("--get---");
		User user = new User();
		user.setId(id);
		return user;
	}

	/**
	 * 按key存储
	 * 前置存储条件 condition 为true 存储
	 * 后置执行条件 unless  为false 存储
	 * 
	 * @param user
	 * @return
	 */
	@CachePut(key = "#user.id", condition = "#user.id<10000", unless = "#result.name.length() < 3")
	public User save(User user) {
		user.setName("name - " + user.getId());
		return user;
	}

	/**
	 * 按key删除
	 * 
	 * @param id
	 * @return
	 */
	@CacheEvict(key = "#id")
	public String del(Integer id) {
		System.out.println("--del---");
		return String.valueOf(id);
	}

	/**
	 * 清除全部
	 */
	@CacheEvict(allEntries = true)
	public void removeAll() {
	}
	
	/**
	 * 返回所有的缓存信息
	 * @return
	 */
	public Map<String,Cache> cache() {
		Map<String,Cache> map = new HashMap<>();
		for(String name:cacheManager.getCacheNames()) {
			map.put(name, cacheManager.getCache(name));
		}
		return map;
	}

}
