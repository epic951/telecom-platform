package com.epic951.business.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;

@RestController
@ResponseBody
public class TelecomServiceController {

	@Autowired
	private TelecomServiceHandler telecomService;

	@GetMapping(value = "/getservices")
	public List<TelecomService> getAllTelecomServices() {
		return telecomService.getAllTelecomServices();
	}

	@PostMapping(value = "/addservice")
	public String processAddService(@RequestBody TelecomService s) {
		System.err.println(s.getTelecomServiceName());
		TelecomService newService = telecomService.addOrUpdateService(s);
		if (newService != null) {
			return "success";
		}
		return "failure";
	}

	@Transactional
	@DeleteMapping(value = "/deleteservicebyname")
	public String processDeleteTelecomService(@RequestBody TelecomService s) {
		if (telecomService.deleteServiceByTelecomServiceName(s.getTelecomServiceName()) == 1) {
			return "success";
		}
		return "failure";
	}

	@Transactional
	@PutMapping(value = "/updateservice")
	public String processUpdateTelecomService(@RequestBody TelecomService s) {
		if (telecomService.addOrUpdateService(s) != null) {
			return "success";
		}
		return "failure";
	}

}
