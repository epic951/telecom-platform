package com.epic951.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epic951.business.services.OperatorService;
import com.epic951.data.entities.Operator;

public class OperatorController {

	@Autowired
	private OperatorService operatorService;

	@GetMapping(value = "/getoperators")
	public List<Operator> getAllOperator() {
		return operatorService.getAllOperators();
	}

	@PostMapping(value = "/addoperator")
	@ResponseBody
	public String processAddOperator(@RequestBody Operator o) {
		Operator newOperator = operatorService.addOperator(o);
		if (newOperator != null) {
			return "success";
		}
		return "failure";
	}
}
