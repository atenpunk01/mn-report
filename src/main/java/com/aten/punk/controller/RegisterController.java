package com.aten.punk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class RegisterController {

	private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

	@GetMapping("/")
	public String viewHomeController() throws Throwable {
		log.info("### viewHomeController ###");
		return "home.xhtml";
	}

}
