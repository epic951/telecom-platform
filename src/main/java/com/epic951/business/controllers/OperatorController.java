package com.epic951.business.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

	@Autowired
	private OperatorService operatorService;

	@CrossOrigin
	@GetMapping(value = "/getoperators")
	public List<Operator> getAllOperator() {
		return operatorService.getAllOperators();
	}

	@PostMapping(value = "/addoperator")
	public String processAddOperator(@RequestBody Operator o) {
		Operator newOperator = operatorService.addOrUpdateOperator(o);
		if (newOperator != null) {
			return "success";
		}
		return "failure";
	}

	@Transactional
	@DeleteMapping(value = "/deleteoperatorbyname")
	public String processDeleteOperatorByOperatorName(@RequestBody Operator o) {
		if (operatorService.deleteOperatorByOperatorName(o.getOperatorName()) == 1) {
			return "success";
		}
		return "failure";
	}

	@Transactional
	@PutMapping(value = "/updateoperator")
	public String processUpdateOperator(@RequestBody Operator o) {
		if (operatorService.addOrUpdateOperator(o) != null) {
			return "success";
		}
		return "failure";
	}
}
