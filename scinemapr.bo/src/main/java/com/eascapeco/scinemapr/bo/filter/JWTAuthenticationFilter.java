package com.eascapeco.scinemapr.bo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eascapeco.scinemapr.bo.controller.auth.BoAuthController;
import com.eascapeco.scinemapr.bo.service.AdminUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.eascapeco.scinemapr.api.util.CookieUtils;
import com.eascapeco.scinemapr.bo.security.JwtTokenProvider;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.text.normalizer.ICUBinary;

/**
 * JWT Authentication Filter
 * 
 * @author jaehankim
 * @date 2019. 10. 27
 */

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";

	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private AdminUserDetailsService adminUserDetailsService;
	public JWTAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader(JWT_TOKEN_HEADER_PARAM);

		String username = null;
		String jwtToken = null;

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);

			try {
				username = jwtTokenProvider.getUsernameFromToken(jwtToken);
				log.info("유저명 " + username);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (JWTVerificationException e) {
				System.out.println("JWT Token has expired");
			}

		} else {
			log.debug("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		if (username != null) {
			UserDetails userDetails = this.adminUserDetailsService.loadUserByUsername(username);
			// if token is valid configure Spring Security to manually set authentication
//			if (jwtTokenProvider.validateToken(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			}
		}
		filterChain.doFilter(request, response);
	}
}
