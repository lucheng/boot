package org.cheng.user.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Bean;

//import com.netflix.loadbalancer.IRule;
//import com.netflix.loadbalancer.RandomRule;

@EnableCaching
@EnableCircuitBreaker
@EnableHystrix
@EnableHystrixDashboard
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class UserClientApplication 
{
//	改变ribbon负载的策略
//	@Bean
//	public IRule ribbonRule() {
//		return new RandomRule(); //随机
//	}
	
    public static void main( String[] args )
    {
    	SpringApplication.run(UserClientApplication.class, args);
    }
}
