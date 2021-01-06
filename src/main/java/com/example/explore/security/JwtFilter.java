package com.example.explore.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class JwtFilter extends GenericFilterBean {

	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	private final AppUserDetailsService userDetailsService;

	public JwtFilter(AppUserDetailsService userDetailsService) {
	        this.userDetailsService = userDetailsService;
	    }

	/**
	 * Determine if there is a JWT as part of the HTTP Request Header. If it is
	 * valid then set the current context With the Authentication(user and roles)
	 * found in the token
	 *
	 * @param req         Servlet Request
	 * @param res         Servlet Response
	 * @param filterChain Filter Chain
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		logger.info("Process request to check for a JSON Web Token ");
		String headerValue = ((HttpServletRequest) req).getHeader(SecurityConstants.HEADER_STRING);
		getBearerToken(headerValue).ifPresent(token -> {
			userDetailsService.loadUserByJwtToken(token).ifPresent(userDetails -> {
				SecurityContextHolder.getContext().setAuthentication(
						new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
			});
		});
		filterChain.doFilter(req, res);
	}

	/**
	 * if present, extract the jwt token from the "Bearer <jwt>" header value.
	 *
	 * @param headerVal
	 * @return jwt if present, empty otherwise
	 */
	private Optional<String> getBearerToken(String headerVal) {
		if (headerVal != null && headerVal.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			return Optional.of(headerVal.replace(SecurityConstants.TOKEN_PREFIX, "").trim());
		}
		return Optional.empty();
	}

}
