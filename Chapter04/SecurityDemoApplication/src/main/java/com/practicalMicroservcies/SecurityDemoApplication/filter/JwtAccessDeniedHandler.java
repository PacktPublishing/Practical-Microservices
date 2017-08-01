package com.practicalMicroservcies.SecurityDemoApplication.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		ObjectMapper jsonMapper = new ObjectMapper();
		response.setContentType("application/json");
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		PrintWriter out = response.getWriter();
		Map<String, String> jsonResponse = new HashMap<String, String>();
		jsonResponse.put("msg", "Insufficient Scope");
		out.write(jsonMapper.writeValueAsString(jsonResponse));
		out.flush();
		out.close();

	}

}
