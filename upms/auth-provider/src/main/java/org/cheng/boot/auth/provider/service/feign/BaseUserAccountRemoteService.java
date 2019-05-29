package org.cheng.boot.auth.provider.service.feign;

import org.cheng.boot.common.core.model.ResultBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "base-provider", fallback = BaseUserAccountRemoteService.HystrixClientFallback.class)
public interface BaseUserAccountRemoteService {
	
	@PostMapping("/account/localLogin")
	public ResultBody<Object> localLogin(@RequestParam(value = "username") String username);
	
	@Component
	public class HystrixClientFallback implements BaseUserAccountRemoteService {
		@Override
		public ResultBody<Object> localLogin(String username) {
			return new ResultBody<Object>();
		}
	}
}
