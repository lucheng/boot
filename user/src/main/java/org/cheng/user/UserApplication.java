package org.cheng.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class UserApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(UserApplication.class, args);
    }
}
