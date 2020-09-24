package com.eascapeco.scinemapr.bo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.bo.service.AdminUserDetailsService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.eascapeco.scinemapr.bo.security.JwtTokenProvider;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT Authentication Filter
 *
 * @author jaehankim
 * @date 2019. 10. 27
 */

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    public static final String JWT_TOKEN_HEADER_PARAM = "Authorization";

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AdminUserDetailsService adminUserDetailsService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader(JWT_TOKEN_HEADER_PARAM);
        log.info("AccessToken : {}", requestTokenHeader);

        String username = null;
        String jwtToken = null;

//        Checking Token
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);

            try {
//                Extract ID after JWT parsing
                username = jwtTokenProvider.getUsernameFromToken(jwtToken);
                log.info("userId : {}", username);
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token", e);
            } catch (TokenExpiredException e) {
                log.error("JWT Token has expired", e);
            }

        } else {
            log.debug("JWT Token does not begin with Bearer String");
        }

        // Once we get the token validate it.
        if (!StringUtils.isEmpty(username)) {
            Admin chkAdm = adminUserDetailsService.loadUserByUsername(username);

//            if token is valid configure Spring Security to manually set authentication
            if (jwtTokenProvider.validateToken(jwtToken, chkAdm)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(chkAdm, null, chkAdm.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
