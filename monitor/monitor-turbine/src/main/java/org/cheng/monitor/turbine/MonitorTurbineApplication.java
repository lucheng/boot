package org.cheng.monitor.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine
@SpringBootApplication
public class MonitorTurbineApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(MonitorTurbineApplication.class, args);
    }
}
