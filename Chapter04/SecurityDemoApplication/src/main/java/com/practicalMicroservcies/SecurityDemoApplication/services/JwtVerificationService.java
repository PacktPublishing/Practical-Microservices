package com.practicalMicroservcies.SecurityDemoApplication.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jose4j.jwk.HttpsJwks;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.keys.resolvers.HttpsJwksVerificationKeyResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This service is used to verify the JWT token verification.
 * 
 * @author umesh
 *
 */
@Service
public class JwtVerificationService implements UserDetailsService {
	private static final Logger logger = Logger.getLogger(JwtVerificationService.class);
	private static final String CLASS_NAME = "JWTVerificationService";

	@Value("${JwksUrl}")
	protected String jwksBaseURL;

	@Value("${JwksIssuer}")
	protected String jwksIssuer;

	@Value("${JwksAudience}")
	protected String jwksAudience;

	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
		String username = "";
		String role = "";
		JwtClaims jwtClaims = null;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			jwtClaims = getJwtClaims(token);
			username = (String) jwtClaims.getClaimsMap().get("client_id");
			logger.debug(CLASS_NAME + "userName :" + jwtClaims.getClaimsMap().get("client_id"));
			role = (String) jwtClaims.getClaimsMap().get("scope");
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			logger.debug(CLASS_NAME + "JWT validation succeeded! with Scope " + role);
		} catch (ClassCastException e) {
			logger.debug("Not able to type cast Scope in String.");
		} catch (Exception e) {
			logger.debug("Invalid JWT !!! token = {" + token + "} found and exception = ", e);
		}
		if (username != null && username.length() > 0)
			return (UserDetails) new User(username, "", authorities);
		else
			return null;
	}

	private JwtClaims getJwtClaims(String token) {
		HttpsJwks httpsJkws = new HttpsJwks(jwksBaseURL);
		HttpsJwksVerificationKeyResolver httpsJwksKeyResolver = new HttpsJwksVerificationKeyResolver(httpsJkws);
		JwtConsumer jwtConsumer = new JwtConsumerBuilder().setRequireExpirationTime().setAllowedClockSkewInSeconds(3600)
				.setExpectedIssuer(jwksIssuer)
				// whom the JWT needs to have been issued by
				.setExpectedAudience(jwksAudience).setVerificationKeyResolver(httpsJwksKeyResolver).build();
		try {
			// Validate the JWT and process it to the Claims
			JwtClaims jwtClaims = jwtConsumer.processToClaims(token);

			return jwtClaims;
		} catch (InvalidJwtException e) {
			// Anyway here throws the exception , so no need to log the error.
			// log the error if required from where this function invokes
			// logger.error("Invalid JWT! " + e);
			throw new AuthenticationServiceException("Invalid Token");
		}
	}
}
