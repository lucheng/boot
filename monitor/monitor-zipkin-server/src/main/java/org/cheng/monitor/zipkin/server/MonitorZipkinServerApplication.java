package org.cheng.monitor.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@EnableDiscoveryClient
@SpringBootApplication
public class MonitorZipkinServerApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(MonitorZipkinServerApplication.class, args);
    }
}
