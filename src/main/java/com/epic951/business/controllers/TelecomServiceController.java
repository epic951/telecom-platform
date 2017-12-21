package com.epic951.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;

public class TelecomServiceController {

	@Autowired
	private TelecomServiceHandler telecomService;

	@GetMapping(value = "/getservices")
	public List<TelecomService> getAllTelecomServices() {
		return telecomService.getAllTelecomServices();
	}

	@PostMapping(value = "/addservice")
	@ResponseBody
	public String processAddService(@RequestBody TelecomService s) {
		TelecomService newService = telecomService.addService(s);
		if (newService != null) {
			return "success";
		}
		return "failure";
	}
}
