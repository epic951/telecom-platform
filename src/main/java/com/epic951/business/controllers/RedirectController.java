package com.epic951.business.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

	@GetMapping(value = "/")
	public String forwardToAngularFrontEnd() {
		return "redirect:https://telecom-platform-frontend.herokuapp.com/";
	}
}
