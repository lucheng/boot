package org.cheng.boot.base.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "org.cheng.boot.base.provider.mapper")
public class BaseApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(BaseApplication.class, args);
    }
}
