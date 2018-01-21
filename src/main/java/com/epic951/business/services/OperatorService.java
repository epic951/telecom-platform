package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;
import com.epic951.utilities.TestUtilities;

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
			newOperator = operatorRepository.save(initializeOperator(o, "Create"));
			System.err.println(newOperator.toString());
			return newOperator;
		}
		if (viableForUpdate) {
			newOperator = operatorRepository.save(initializeOperator(o, "Update"));
		}
		return newOperator;
	}

	public Integer deleteOperator(Operator o) {
		if (o.getOperatorName() != null && !o.getOperatorName().isEmpty()) {
			return operatorRepository.deleteByOperatorName(o.getOperatorName());
		} else if (o.getOperatorId() > 0) {
			return operatorRepository.deleteByOperatorId(o.getOperatorId());
		}
		return 0;
	}

	public List<Operator> getAllOperators() {
		List<Operator> operators = new ArrayList<>();
		operatorRepository.findAll().forEach(operators::add);
		return operators;
	}

	private Operator initializeOperator(Operator o, String status) {
		Operator temp = null;
		if (status.toLowerCase().equals("update")) {
			temp = operatorRepository.findByOperatorId(o.getOperatorId()).get();
		} else if (status.toLowerCase().equals("create")) {
			temp = TestUtilities.createTestOperator(0, null, "Default", 1);
		}
		temp = TestUtilities.createTestOperator(o.getOperatorId(), o.getOperatorName(),
				(o.getOperatorCountry() == null || o.getOperatorCountry().isEmpty() ? temp.getOperatorCountry()
						: o.getOperatorCountry()),
				(o.getRating() <= 0 ? temp.getRating() : o.getRating()));
		return temp;
	}

}
