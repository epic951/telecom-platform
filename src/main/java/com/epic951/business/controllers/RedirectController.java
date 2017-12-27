package com.epic951.business.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class RedirectController {

	@GetMapping(value = "/")
	public String forwardToAngularFrontEnd() {
		return "forward:/https://telecom-platform-frontend.herokuapp.com/";
	}
}
