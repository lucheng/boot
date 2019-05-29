package org.cheng.gateway.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.oauth2.server.resource.web.server.ServerBearerTokenAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationEntryPointFailureHandler;

@Configuration
public class ResourceServerConfiguration {
	@Bean
	public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
//		AuthenticationWebFilter oauth2 = new AuthenticationWebFilter();
//		oauth2.setServerAuthenticationConverter(new ServerBearerTokenAuthenticationConverter());
//		oauth2.setAuthenticationFailureHandler(new ServerAuthenticationEntryPointFailureHandler(entryPoint));
//		http.httpBasic().disable().csrf().disable().authorizeExchange().anyExchange().access(apiAuthorizationManager)
//				.and()
//				.exceptionHandling()
//				.accessDeniedHandler(accessDeniedHandler)
//				.authenticationEntryPoint(entryPoint)
//				.and()
//				// oauth2认证过滤器
//				.addFilterAt(oauth2, SecurityWebFiltersOrder.AUTHENTICATION);
		return http.build();
	}
}
