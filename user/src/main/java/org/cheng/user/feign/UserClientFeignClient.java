package org.cheng.user.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Param;
import feign.hystrix.FallbackFactory;

@FeignClient(name = "USER-CLIENT", fallbackFactory = UserClientFeignClient.HystrixClientFallbackFactory.class)
public interface UserClientFeignClient {

	@RequestMapping(value = "/user/get", method = RequestMethod.GET)
	public String get(@Param("id") Integer id);

	/*
	 * @Component class HystrixClientFallback implements UserClientFeignClient {
	 * 
	 * @Override public String get(Integer id) { return "服务USER-CLIENT中断"; }
	 * 
	 * }
	 */

	@Component
	class HystrixClientFallbackFactory implements FallbackFactory<UserClientFeignClient> {

		private static final Logger LOGGER = LoggerFactory.getLogger(HystrixClientFallbackFactory.class);

		@Override
		public UserClientFeignClient create(Throwable cause) {

			LOGGER.warn(cause.getMessage(), cause);

			return new UserClientFeignClient() {
				@Override
				public String get(Integer id) {
					return "服务USER-CLIENT中断";
				}
			};
		}
	}
}
