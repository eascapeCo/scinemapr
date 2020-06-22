package com.eascapeco.scinemapr.bo.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.eascapeco.scinemapr.api.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "zxcz");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=utf-8");

		Result result = new Result();
		result.setCode("401");
		result.setMessage("UNAUTHORIZED");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String test = mapper.writeValueAsString(result);
		
		System.out.println(test);
		PrintWriter out = response.getWriter();
		out.print(test);
	}

}
