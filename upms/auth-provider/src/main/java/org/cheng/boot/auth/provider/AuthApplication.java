package org.cheng.boot.auth.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AuthApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(AuthApplication.class, args);
    }
}
