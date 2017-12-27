package com.epic951.business.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {

	@GetMapping(value = "/")
	public ModelAndView forwardToAngularFrontEnd() {
		return new ModelAndView("forward:https://telecom-platform-frontend.herokuapp.com");
		// return "redirect:https://telecom-platform-frontend.herokuapp.com/";
	}
}
