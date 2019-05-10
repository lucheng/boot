package org.cheng.user.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.hystrix.FallbackFactory;

@FeignClient(name = "ADMIN", fallbackFactory = AdminServerFeignClient.HystrixClientFallback.class)
public interface AdminServerFeignClient {

	@RequestMapping(value = "/admin/{id}", method = RequestMethod.GET)
	public String admin(@PathVariable("id") Integer id);

	@Component
	class HystrixClientFallback implements FallbackFactory<AdminServerFeignClient> {
		
		private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallback.class);

		@Override
		public AdminServerFeignClient create(Throwable cause) {
			
			LOGGER.warn(cause.getMessage(),cause);
			
			return new AdminServerFeignClient() {
				@Override
				public String admin(Integer id) {
					return "服务ADMIN中断";
				}
			};
		}

	}
}
