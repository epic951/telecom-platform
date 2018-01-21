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

@RestController
@RequestMapping(value = "/api")
@ResponseBody
public class TelecomServiceController {

	private ResponseEntity<String> response;

	@Autowired
	private TelecomServiceHandler telecomService;

	@GetMapping(value = "/getservices")
	public ResponseEntity<String> getAllTelecomServices() {
		List<TelecomService> body = telecomService.getAllTelecomServices();
		response = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/findservice/{id}")
	public ResponseEntity<String> findServiceById(@PathVariable(value = "id") int id) {
		TelecomService body = telecomService.findServiceById(id);
		response = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/addservice")
	public ResponseEntity<String> processAddService(@RequestBody TelecomService s) {
		System.err.println(s.getTelecomServiceName());
		TelecomService newService = telecomService.addOrUpdateService(s);
		if (newService != null) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

	@Transactional
	@DeleteMapping(value = "/deleteservicebyname")
	public ResponseEntity<String> processDeleteTelecomService(@RequestBody TelecomService s) {
		if (telecomService.deleteServiceByTelecomServiceName(s.getTelecomServiceName()) == 1) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

	@Transactional
	@PutMapping(value = "/updateservice")
	public ResponseEntity<String> processUpdateTelecomService(@RequestBody TelecomService s) {
		if (telecomService.addOrUpdateService(s) != null) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

}
