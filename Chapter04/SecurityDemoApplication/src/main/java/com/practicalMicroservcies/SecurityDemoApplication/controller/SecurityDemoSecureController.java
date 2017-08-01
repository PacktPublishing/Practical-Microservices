package com.practicalMicroservcies.SecurityDemoApplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/PM/")
public class SecurityDemoSecureController {
	@RequestMapping(method = RequestMethod.GET, value = "/secure/greet", produces = "application/json")
	public String getSecureHello() {
		return "hey! secure";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/unsecure/greet", produces = "application/json")
	public String getUnSecureHello() {
		return "hey! unsecure";
	}
}
