package org.cheng.user.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "USER")
public interface UserFeignClient {
	@RequestMapping(value = "/port/{id}", method = RequestMethod.GET)
	public String port(@PathVariable("id") Integer id);
}
