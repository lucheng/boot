package org.cheng.boot.auth.config;

import javax.sql.DataSource;

import org.cheng.boot.auth.service.security.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Authorization Server
 * 
 * @author Cheng
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTokenStore tokenStore;

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserServiceDetail userServiceDetail;

	@Bean
	public JdbcTokenStore getJdbcTokenStore() {
		return new JdbcTokenStore(dataSource);
	}

	/**
	 * 配置客户端信息
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
		.jdbc(dataSource);//  客户端的配置信息放在数据库中

//		.inMemory()  //  客户端的配置信息放在内存中 
//		.withClient("browser").authorizedGrantTypes("refresh_token", "password").scopes("ui").and()
//		.withClient("service-hi").secret("{noop}123456").authorizedGrantTypes("client_credentials", "refresh_token", "password").scopes("server");
	}

	/**
	 * 配置授权 Token 节点和 oken 服务
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(tokenStore).authenticationManager(authenticationManager)
				.userDetailsService(userServiceDetail);
	}

	/**
	 * 配置 Token 节点的安全策略
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");

	}

	/*
	 * @Bean public static PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 */
}
