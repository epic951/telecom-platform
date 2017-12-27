package com.epic951.business.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

	@GetMapping(value = "/")
	public String forwardToAngularFrontEnd(HttpServletResponse response) {
		// response.setHeader("Location",
		// "https://telecom-platform-frontend.herokuapp.com/");
		return "redirect:https://telecom-platform-frontend.herokuapp.com/";
	}
}
