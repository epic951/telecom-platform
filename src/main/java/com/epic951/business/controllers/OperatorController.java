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

import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;

@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class OperatorController {

	private ResponseEntity<String> response;

	@Autowired
	private OperatorService operatorService;

	@GetMapping(value = "/getoperators")
	public ResponseEntity<String> getAllOperator() {
		List<Operator> body = operatorService.getAllOperators();
		response = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/findoperator/{id}")
	public ResponseEntity<String> findOpratorById(@PathVariable(value = "id") int id) {
		Operator body = operatorService.findOperatorById(id);
		response = new ResponseEntity<String>(body.toString(), HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/addoperator")
	public ResponseEntity<String> processAddOperator(@RequestBody Operator o) {
		Operator newOperator = operatorService.addOrUpdateOperator(o);
		if (newOperator != null) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

	@Transactional
	@DeleteMapping(value = "/deleteoperatorbyname")
	public ResponseEntity<String> processDeleteOperatorByOperatorName(@RequestBody Operator o) {
		if (operatorService.deleteOperatorByOperatorName(o.getOperatorName()) == 1) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}

	@Transactional
	@PutMapping(value = "/updateoperator")
	public ResponseEntity<String> processUpdateOperator(@RequestBody Operator o) {
		if (operatorService.addOrUpdateOperator(o) != null) {
			response = new ResponseEntity<String>("Success", HttpStatus.OK);
			return response;
		}
		response = new ResponseEntity<String>("Failure", HttpStatus.BAD_REQUEST);
		return response;
	}
}
