package org.cheng.boot.auth.provider.service.feign;

import org.cheng.boot.common.core.model.ResultBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "base-provider", fallbackFactory = BaseUserAccountRemoteService.HystrixClientFallback.class)
public interface BaseUserAccountRemoteService {
	
	@PostMapping("/account/localLogin") @RequestMapping(value = "/port/{id}", method = RequestMethod.GET)
	public ResultBody<Object> port(@PathVariable("id") Integer id);
	
	@Component
	public class HystrixClientFallback implements BaseUserAccountRemoteService {
		@Override
		public ResultBody<Object> port(Integer id) {
			return new ResultBody<Object>();
		}
	}
}
