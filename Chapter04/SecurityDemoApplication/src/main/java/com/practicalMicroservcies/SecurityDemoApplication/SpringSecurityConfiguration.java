package com.practicalMicroservcies.SecurityDemoApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.practicalMicroservcies.SecurityDemoApplication.filter.JwtAccessDeniedHandler;
import com.practicalMicroservcies.SecurityDemoApplication.filter.JwtAuthenticationTokenFilter;
import com.practicalMicroservcies.SecurityDemoApplication.filter.JwtEntryPoint;
import com.practicalMicroservcies.SecurityDemoApplication.services.JwtVerificationService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtEntryPoint unauthorizedHandler;
	@Autowired
	private JwtVerificationService jwtVerificationService;

	@Autowired
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.jwtVerificationService);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public AccessDeniedHandler getJwtAccessDeniedHandler() {
		JwtAccessDeniedHandler handler = new JwtAccessDeniedHandler();
		return handler;
	}

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		JwtAuthenticationTokenFilter authenticationTokenFilter = new JwtAuthenticationTokenFilter();
		authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
		return authenticationTokenFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/PM/secure/**").access("hasAnyRole('ROLE_Secure_Access')")
				.antMatchers(HttpMethod.POST, "/PM/secure/**").access("hasAnyRole('ROLE_Secure_Acces')").and()
				.exceptionHandling().accessDeniedHandler(getJwtAccessDeniedHandler())
				.authenticationEntryPoint(unauthorizedHandler);
		http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		http.csrf().disable();
		http.headers().cacheControl();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/PM/unsecure/**");
	}
}
