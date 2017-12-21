package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	public Operator addOperator(Operator o) {
		// Validation is required ..
		if (o.getOperator_name() != null && !o.getOperator_name().isEmpty()) {
			Operator newOperator = operatorRepository.save(o);
			return newOperator;
		}
		return null;
	}

	public List<Operator> getAllOperators() {
		List<Operator> operators = new ArrayList<>();
		operatorRepository.findAll().forEach(operators::add);
		return operators;
	}
}
