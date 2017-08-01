package com.practicalMicroservcies.SecurityDemoApplication.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	AuthenticationManagerBuilder authBuilder;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;

		String header_authorization = httpServletRequest.getHeader("Authorization");
		String token = (StringUtils.isBlank(header_authorization) ? null : header_authorization.split(" ")[1]);

		if (StringUtils.isBlank(header_authorization) && token == null) {
			logger.info("Token Not found in header.");
		} else {

			UserDetails principal = null;
			try {
				principal = authBuilder.getDefaultUserDetailsService().loadUserByUsername(token);
				UsernamePasswordAuthenticationToken userAuthenticationToken = new UsernamePasswordAuthenticationToken(
						principal, "", principal.getAuthorities());
				userAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
			} catch (Exception e) {
				HttpServletResponse httpresposne = (HttpServletResponse) response;
				httpresposne.setContentType("application/json");
				httpresposne.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				ObjectMapper jsonMapper = new ObjectMapper();
				PrintWriter out = httpresposne.getWriter();
				Map<String, String> jsonResponse = new HashMap<String, String>();
				jsonResponse.put("msg", "Invalid Token");
				out.write(jsonMapper.writeValueAsString(jsonResponse));
				out.flush();
				out.close();
				return;
			}
			chain.doFilter(request, response);
		}
	}
}
