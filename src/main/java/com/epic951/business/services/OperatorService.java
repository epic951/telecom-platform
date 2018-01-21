package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;

@Service
public class OperatorService {

	private OperatorRepository operatorRepository;

	@Autowired
	public OperatorService(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	public void setOperatorRepository(OperatorRepository operatorRepository) {
		this.operatorRepository = operatorRepository;
	}

	public Operator findOperatorById(int id) {
		return operatorRepository.findByOperatorId(id).get();
	}

	public Operator addOrUpdateOperator(Operator o) {
		// Validation is required ..
		Operator newOperator = null;
		boolean alreadyAdded = operatorRepository.findByOperatorName(o.getOperatorName()).isPresent();
		boolean viableForUpdate = operatorRepository.findByOperatorId(o.getOperatorId()).isPresent();
		if (!alreadyAdded && o.getOperatorName() != null && !o.getOperatorName().isEmpty()) {
			newOperator = operatorRepository.save(o);
			System.err.println(newOperator.toString());
			return newOperator;
		}
		if (viableForUpdate) {
			newOperator = operatorRepository.save(o);
		}
		return newOperator;
	}

	public Integer deleteOperatorByOperatorName(String name) {
		return operatorRepository.deleteByOperatorName(name);
	}

	public List<Operator> getAllOperators() {
		List<Operator> operators = new ArrayList<>();
		operatorRepository.findAll().forEach(operators::add);
		return operators;
	}

}
