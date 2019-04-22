package org.cheng.user.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "USER", fallback = UserFeignClient.HystrixClientFallback.class)
public interface UserFeignClient {

	@RequestMapping(value = "/port/{id}", method = RequestMethod.GET)
	public String port(@PathVariable("id") Integer id);
	
	@Component
	class HystrixClientFallback implements UserFeignClient {

		@Override
		public String port(Integer id) {
			return "服务USER中断";
		}
		
	}
}





