package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.Operator;
import com.epic951.data.repositories.OperatorRepository;
import com.epic951.utilities.HTTPUtilities;
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
		if (!alreadyAdded) {
			newOperator = validateParameters(o, "create");
			return newOperator;
		}
		if (viableForUpdate) {
			newOperator = validateParameters(o, "update");
		}
		setValidationErrors(o, "empty");
		return newOperator;
	}

	public Integer deleteOperator(Operator o) {
		if (!compareStrings(o.getOperatorName(), null)) {
			return operatorRepository.deleteByOperatorName(o.getOperatorName());
		} else if (o.getOperatorId() > 0) {
			return operatorRepository.deleteByOperatorId(o.getOperatorId());
		}
		setValidationErrors(o, "delete");
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
			temp = TestUtilities.createTestOperator(0, "default-operator-name", "default-operator-country",
					"https://openclipart.org/download/22436/nicubunu-Tools.svg", 1);
		}
		temp = TestUtilities.createTestOperator(o.getOperatorId(),
				(compareStrings(o.getOperatorName(), null) ? temp.getOperatorName() : o.getOperatorName()),
				(compareStrings(o.getOperatorCountry(), null) ? temp.getOperatorCountry() : o.getOperatorCountry()),
				(compareStrings(o.getImageUrl(), null) || o.getImageUrl().equals("") ? temp.getImageUrl()
						: o.getImageUrl()),
				(o.getRating() <= 0 ? temp.getRating() : o.getRating()));
		return temp;
	}

	private boolean compareStrings(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	private Operator validateParameters(Operator o, String status) {
		Operator temp = null;
		if (compareStrings(o.getOperatorName(), null)) {
			setValidationErrors(o, "empty");
		} else {
			temp = operatorRepository.save(initializeOperator(o, status));
		}
		return temp;
	}

	private void setValidationErrors(Operator o, String field) {
		switch (field.toLowerCase()) {
		case "empty":
			if (compareStrings(o.getOperatorName(), null)) {
				HTTPUtilities.setErrors("Operator Name can not be empty or null");
				HTTPUtilities.setErrorMessage("Operator name is a required field and can not be empty");
			}
			if (o.getOperatorId() <= 0) {
				HTTPUtilities.setErrors("Operator ID can not be empty or null");
				HTTPUtilities.setErrorMessage("Operator ID is required to perform updating");
			}
			break;
		case "delete":
			HTTPUtilities.setErrors(new ArrayList<>());
			HTTPUtilities.setErrors("Operator ID & Operator Name can not both be empty or null");
			HTTPUtilities.setErrorMessage("Either Operator ID or Operator Name is required to perform deletion");
			break;
		default:
			break;
		}

	}

}
