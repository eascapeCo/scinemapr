package com.eascapeco.scinemapr.bo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eascapeco.scinemapr.api.constants.ErrorCode;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.ErrorResponse;
import com.eascapeco.scinemapr.bo.service.AdminUserDetailsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
 * @Date 2019. 10. 27
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
        log.info("requestTokenHeader : {}", requestTokenHeader);

        String username = null;
        String jwtToken = null;

        try {
            //        Checking Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);
                //            Extract ID after JWT parsing
                username = jwtTokenProvider.getUsernameFromToken(jwtToken);
                log.info("userId : {}", username);

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

        } catch (ExpiredJwtException e) {
            ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST.getCode(), ErrorCode.BAD_REQUEST.getDescription(), e.getMessage());

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            response.getWriter().write(convertObjectToJson(errorResponse));
            response.getWriter().flush();
            response.getWriter().close();

        }
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
