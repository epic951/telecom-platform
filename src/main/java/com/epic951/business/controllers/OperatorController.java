package com.epic951.business.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.json.JSONException;
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
import com.epic951.utilities.HTTPUtilities;

@RestController
@ResponseBody
@RequestMapping(value = "/api")
public class OperatorController {

	private ResponseEntity<List<Operator>> listResponse;
	private ResponseEntity<Operator> singleResponse;

	@Autowired
	private OperatorService operatorService;

	@GetMapping(value = "/getoperators")
	public ResponseEntity<List<Operator>> getAllOperator() {
		List<Operator> body = operatorService.getAllOperators();
		listResponse = new ResponseEntity<List<Operator>>(body, HttpStatus.OK);
		return listResponse;
	}

	@GetMapping(value = "/findoperator/{id}")
	public ResponseEntity<Operator> findOpratorById(@PathVariable(value = "id") int id) {
		Operator body = operatorService.findOperatorById(id);
		singleResponse = new ResponseEntity<Operator>(body, HttpStatus.OK);
		return singleResponse;
	}

	@PostMapping(value = "/addoperator")
	public ResponseEntity<String> processAddOperator(@RequestBody Operator o) throws JSONException {
		Operator newOperator = operatorService.addOrUpdateOperator(o);
		if (newOperator != null) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

	@Transactional
	@DeleteMapping(value = "/deleteoperator")
	public ResponseEntity<String> processDeleteOperatorByOperatorName(@RequestBody Operator o) throws JSONException {
		if (operatorService.deleteOperator(o) == 1) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}

	@Transactional
	@PutMapping(value = "/updateoperator")
	public ResponseEntity<String> processUpdateOperator(@RequestBody Operator o) throws JSONException {
		if (operatorService.addOrUpdateOperator(o) != null) {
			return HTTPUtilities.handleResponse("Success");
		}
		return HTTPUtilities.handleResponse("Failure");
	}
}
