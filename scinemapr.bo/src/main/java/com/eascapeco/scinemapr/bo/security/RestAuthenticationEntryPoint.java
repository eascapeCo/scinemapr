package com.eascapeco.scinemapr.bo.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.eascapeco.scinemapr.api.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json;charset=utf-8");

		Result result = new Result();
		result.setCode("401");
		result.setMessage("UNAUTHORIZED");
		
		ObjectMapper mapper = new ObjectMapper();
		
		String test = mapper.writeValueAsString(result);

		System.out.println(request.getContentType());
		System.out.println(test);
		PrintWriter out = response.getWriter();
		out.print(test);
		//response.sendRedirect("/error");
	}

}
