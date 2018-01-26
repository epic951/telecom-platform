package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;
import com.epic951.utilities.HTTPUtilities;
import com.epic951.utilities.TestUtilities;

@Service
public class TelecomServiceHandler {

	private TelecomServiceRepository serviceRepository;

	@Autowired
	public TelecomServiceHandler(TelecomServiceRepository serviceRepository) {
		this.serviceRepository = serviceRepository;
	}

	public void setTelecomServiceRepository(TelecomServiceRepository telecomServiceRepository) {
		this.serviceRepository = telecomServiceRepository;
	}

	public TelecomService addOrUpdateService(TelecomService s) {
		// Validation is required ..
		TelecomService newService = null;
		boolean alreadyAdded = serviceRepository.findByTelecomServiceName(s.getTelecomServiceName()).isPresent();
		boolean viableForUpdate = serviceRepository.findByTelecomServiceId(s.getTelecomServiceId()).isPresent();
		if (!alreadyAdded) {
			newService = validateParameters(s, "create");
		}
		if (viableForUpdate) {
			if (s.getTelecomServiceId() <= 0) {
				setValidationErrors(s, "update");
			}
			newService = validateParameters(s, "update");
		}
		return newService;
	}

	public TelecomService findServiceById(int id) {
		return serviceRepository.findByTelecomServiceId(id).get();
	}

	public Integer deleteService(TelecomService s) {
		if (!compareStrings(s.getTelecomServiceName(), null)) {
			return serviceRepository.deleteByTelecomServiceName(s.getTelecomServiceName());
		} else if (s.getTelecomServiceId() > 0) {
			return serviceRepository.deleteByTelecomServiceId(s.getTelecomServiceId());
		}
		setValidationErrors(s, "delete");
		return 0;
	}

	public List<TelecomService> getAllTelecomServices() {
		List<TelecomService> services = new ArrayList<>();
		serviceRepository.findAll().forEach(services::add);
		return services;
	}

	private TelecomService initializeProduct(TelecomService s, String status) {
		TelecomService temp = null;
		System.err.println(s.toString());
		if (status.toLowerCase().equals("update")) {
			temp = serviceRepository.findByTelecomServiceId(s.getTelecomServiceId()).get();
		} else if (status.toLowerCase().equals("create")) {
			temp = TestUtilities.createTestTelecomService(0, "default-telecom-service-name", "default-operator-name",
					false, 0, 1, 1, 1);
		}
		temp = TestUtilities.createTestTelecomService(s.getTelecomServiceId(),
				(compareStrings(s.getTelecomServiceName(), null) ? temp.getTelecomServiceName()
						: s.getTelecomServiceName()),
				(compareStrings(s.getOperatorName(), null) ? temp.getOperatorName() : s.getOperatorName()),
				s.isTelecomServiceType(), (s.getOperatorId() <= 0 ? temp.getOperatorId() : s.getOperatorId()),
				(s.getOperatorServiceId() <= 0 ? temp.getOperatorServiceId() : s.getOperatorServiceId()),
				(s.getOperatorPackageId() <= 0 ? temp.getOperatorPackageId() : s.getOperatorPackageId()),
				(s.getRating() <= 0 ? temp.getRating() : s.getRating()));
		return temp;
	}

	private boolean compareStrings(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	private TelecomService validateParameters(TelecomService s, String status) {
		TelecomService temp = null;
		String operatorName = null;
		String serviceName = null;
		if (!compareStrings(s.getOperatorName(), null)) {
			operatorName = s.getOperatorName().toLowerCase();
		}
		if (!compareStrings(s.getTelecomServiceName(), null)) {
			serviceName = s.getTelecomServiceName().toLowerCase();
		}
		if (compareStrings(operatorName, null) || compareStrings(serviceName, null) || s.getOperatorId() <= 0) {
			setValidationErrors(s, "empty");
		} else if (operatorName.contains("vodafone")) {
			if (s.getOperatorPackageId() > 0 && s.getOperatorServiceId() > 0) {
				temp = serviceRepository.save(initializeProduct(s, status));
			} else {
				setValidationErrors(s, "vodafone");
			}
		} else if (operatorName.contains("etisalat")) {
			if (s.getOperatorPackageId() > 0) {
				temp = serviceRepository.save(initializeProduct(s, status));
			} else {
				setValidationErrors(s, "etisalat");
			}
		} else if (operatorName.contains("orange")) {
			if (s.getOperatorServiceId() > 0) {
				temp = serviceRepository.save(initializeProduct(s, status));
			} else {
				setValidationErrors(s, "orange");
			}
		} else {
			temp = serviceRepository.save(initializeProduct(s, status));
		}
		return temp;
	}

	private void setValidationErrors(TelecomService s, String field) {
		HTTPUtilities.setErrors(new ArrayList<>());
		switch (field.toLowerCase()) {
		case "vodafone":
			if (s.getOperatorPackageId() <= 0) {
				HTTPUtilities.setErrors("Operator Package ID must not be null or less than or equal to zero");
			}
			if (s.getOperatorServiceId() <= 0) {
				HTTPUtilities.setErrors("Operator Service ID must not be null or less than or equal to zero");
			}
			HTTPUtilities.setErrorMessage("Your selected operator (Vodafone) and all its"
					+ " subsidiaries upon creating/modifying a telecom service"
					+ " respectfully require both the Operator Package ID & the "
					+ "Operator Service ID of the service not to be null or less than or equal to zero");
			break;
		case "etisalat":
			if (s.getOperatorPackageId() <= 0) {
				HTTPUtilities.setErrors("Operator Package ID must not be null or 0");
			}
			HTTPUtilities.setErrorMessage("Your selected operator (Etisalat) and all its"
					+ " subsidiaries upon creating/modifying a telecom service respectfully "
					+ "require the Operator Package not to be null or less than zero");
			break;
		case "orange":
			if (s.getOperatorServiceId() <= 0) {
				HTTPUtilities.setErrors("Operator Service ID must not be null or 0");
			}
			HTTPUtilities.setErrorMessage("Your selected operator (Orange) and all its"
					+ " subsidiaries upon creating/modifying a telecom service respectfully "
					+ "require the Operator Service not to be null or less than zero");
			break;
		case "empty":
			if (compareStrings(s.getOperatorName(), null)) {
				HTTPUtilities.setErrors("Operator Name can not be empty");
			}
			if (compareStrings(s.getTelecomServiceName(), null)) {
				HTTPUtilities.setErrors("Telecom Service Name can not be empty");
			}
			if (s.getOperatorId() <= 0) {
				HTTPUtilities.setErrors("Operator ID can not be empty or less than zero");
			}
			HTTPUtilities.setErrorMessage("Neither the Telecom Service name" + " or the Operator name can be empty"
					+ " as well as the Operator ID which can not be empty or less than zero");
			break;
		case "update":
			HTTPUtilities.setErrors("Telecom Service ID can not be empty or null");
			HTTPUtilities.setErrorMessage("Telecom Service ID is required to perform updating");
			break;
		case "delete":
			HTTPUtilities.setErrors("Telecom Service ID & Telecom Service Name can not both be empty or null");
			HTTPUtilities.setErrorMessage(
					"Either Telecom Service ID or Telecom Service Name is required to perform deletion");
			break;
		default:
			break;
		}
	}
}
