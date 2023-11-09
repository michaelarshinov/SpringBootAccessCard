package ru.michaelarshinovhome.Template.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.security.core.context.SecurityContext;

import ru.michaelarshinovhome.Template.service.MAHConnectionService;

@Component
@ConditionalOnExpression(value = "${auth.jwt.enabled}==true")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

	MAHConnectionService MAHConnectionService;
	
	static final String AUTHORIZATION = "Authorization";
	
	public JwtAuthenticationTokenFilter(MAHConnectionService MAHConnectionService) {
		this.MAHConnectionService = MAHConnectionService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		boolean skipAuth = false;
		AntPathMatcher matcher = new AntPathMatcher();
		for (String urlPattern: SecurityConfiguration.AUTH_WHITELIST) {
			if (matcher.match(SecurityConfiguration.SERVER_SERVLET_CONTEXT_PATH + urlPattern, request.getRequestURI())) {
				logger.debug(request.getRequestURI()+" is matched with " + urlPattern);
				skipAuth = true;
				break;
			}
		}
		if (skipAuth) {
			filterChain.doFilter(request, response);
			return;
		}
		String authToken = request.getHeader(AUTHORIZATION);
		logger.info("header names" + request.getHeaderNames());
		logger.info("auth token is : " + authToken);
		String jwtToken = null;
		if (authToken != null && authToken.startsWith("Bearer ")) {
			jwtToken = authToken.substring(7);
			logger.info("Token is: "+jwtToken);
			logger.info("token is valid : "+MAHConnectionService.isTokenValid(jwtToken));
			if (MAHConnectionService.isTokenValid(jwtToken)) {
				filterChain.doFilter(request, response);
				return;
			}
		}
		logger.debug("Unauthorized URI " + request.getRequestURI());
		logger.debug("Unauthorized " + request.getRequestURL());
		((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.");
	}
	
	/*
	 * private void resetAuthenticationAfterRequest() {
	 * SecurityContextHolder.getContext().setAuthentication(null); }
	 */
}
