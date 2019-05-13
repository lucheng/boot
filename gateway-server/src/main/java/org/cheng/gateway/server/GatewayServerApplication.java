package org.cheng.gateway.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.util.pattern.PathPatternParser;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayServerApplication {
	
	@Bean
    public RouteDefinitionLocator discoveryClientRouteDefinitionLocator(DiscoveryClient discoveryClient,DiscoveryLocatorProperties properties) {
        return new DiscoveryClientRouteDefinitionLocator(discoveryClient,properties);
    }
	
	/*
	 * @Bean public CorsWebFilter corsFilter() { CorsConfiguration config = new
	 * CorsConfiguration(); config.addAllowedOrigin("*");
	 * config.addAllowedHeader("*"); config.addAllowedMethod("*");
	 * config.addExposedHeader("x-total-count"); config.setAllowCredentials(true);
	 * 
	 * UrlBasedCorsConfigurationSource source = new
	 * UrlBasedCorsConfigurationSource(new PathPatternParser());
	 * source.registerCorsConfiguration("/**", config); return new
	 * CorsWebFilter(source); }
	 */
	
	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}
}
