package org.cheng.gateway.server.filter;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;

import reactor.core.publisher.Mono;

public class ApiAuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

	@Override
	public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
		return null;
	}
}
