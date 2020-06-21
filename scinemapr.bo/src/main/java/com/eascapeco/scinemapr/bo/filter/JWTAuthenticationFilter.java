package com.eascapeco.scinemapr.bo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;

import com.eascapeco.scinemapr.api.util.CookieUtils;
import com.eascapeco.scinemapr.bo.security.JwtTokenProvider;

/**
 * JWT Authentication Filter
 * 
 * @author jaehankim
 * @date 2019. 10. 27
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

	private JwtTokenProvider jwtTokenProvider;
	
	public JWTAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider; 
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String token = req.getHeader("X-AUTH-TOKEN");

		/*if (token != null && ) {
			
		}*/
		chain.doFilter(request, response);
	}

}
