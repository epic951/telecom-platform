package com.epic951.business.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epic951.business.services.TelecomServiceHandler;
import com.epic951.data.entities.TelecomService;
import com.epic951.utilities.HTTPUtilities;

@RestController
@RequestMapping(value = "/api")
@ResponseBody
public class TelecomServiceController {

	private ResponseEntity<List<TelecomService>> listResponse;
	private ResponseEntity<TelecomService> singleResponse;

	@Autowired
	private TelecomServiceHandler telecomService;

	@GetMapping(value = "/getservices")
	public ResponseEntity<List<TelecomService>> getAllTelecomServices() {
		List<TelecomService> body = telecomService.getAllTelecomServices();
		listResponse = new ResponseEntity<List<TelecomService>>(body, HttpStatus.OK);
		return listResponse;
	}

	@GetMapping(value = "/findservice/{id}")
	public ResponseEntity<TelecomService> findServiceById(@PathVariable(value = "id") int id) {
		TelecomService body = telecomService.findServiceById(id);
		singleResponse = new ResponseEntity<TelecomService>(body, HttpStatus.OK);
		return singleResponse;
	}

	@PostMapping(value = "/addservice")
	public ResponseEntity<String> processAddService(@RequestBody TelecomService s) {
		System.err.println(s.getTelecomServiceName());
		TelecomService newService = telecomService.addOrUpdateService(s);
		if (newService != null) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

	@Transactional
	@DeleteMapping(value = "/deleteservice")
	public ResponseEntity<String> processDeleteTelecomService(@RequestBody TelecomService s) {
		if (telecomService.deleteService(s) == 1) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

	@Transactional
	@PutMapping(value = "/updateservice")
	public ResponseEntity<String> processUpdateTelecomService(@RequestBody TelecomService s) {
		if (telecomService.addOrUpdateService(s) != null) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

}
