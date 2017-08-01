package com.practicalMicroservcies.SecurityDemoApplication.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -7905925380166052059L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// This is invoked when user tries to access a secured REST resource
		// without supplying any credentials
		// We should just send a 401 Unauthorized response because there is no
		// 'login page' to redirect to
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ObjectMapper jsonMapper = new ObjectMapper();
		PrintWriter out = response.getWriter();
		Map<String, String> jsonResponse = new HashMap<String, String>();
		jsonResponse.put("msg", "Invalid Token");
		out.write(jsonMapper.writeValueAsString(jsonResponse));
		out.flush();
		out.close();

	}

}
