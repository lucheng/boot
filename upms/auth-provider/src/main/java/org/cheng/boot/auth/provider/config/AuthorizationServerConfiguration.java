package org.cheng.boot.auth.provider.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * 认证服务配置
 * 
 * @author Cheng
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTokenStore tokenStore;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

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
				// 客户端的配置信息放在数据库中
				.jdbc(dataSource);
	}

	/**
	 * 配置 Token 节点的安全策略
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
				// 开启/oauth/token_key验证端口无权限访问
				.tokenKeyAccess("permitAll()")
				// 开启/oauth/check_token验证端口认证权限访问
				.checkTokenAccess("isAuthenticated()");
	}

	/**
	 * 配置授权 Token 节点和 Token 服务
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				// Token存储
				.tokenStore(tokenStore)
				// 授权管理
				.authenticationManager(authenticationManager)
				// 用户信息
				.userDetailsService(userDetailsService)
				// 自定义确认授权页面
				.pathMapping("/oauth/confirm_access", "/oauth/confirm_access")
				// 自定义错误页
				.pathMapping("/oauth/error", "/oauth/error");
	}
}
