package com.sample.firstboot.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@RequestMapping("/greeting/{userName}")
	String home(@PathVariable("userName") String userName) {
		return "Welcome, " + userName + "!";
	}
}
