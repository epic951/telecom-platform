package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;

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
		if (!alreadyAdded && s.getTelecomServiceName() != null && !s.getTelecomServiceName().isEmpty()) {
			switch (s.getOperatorName()) {
			case "Vodafone":
				if (s.getOperatorPackageId() > 0 && s.getOperatorServiceId() > 0) {
					newService = serviceRepository.save(s);
				}
				break;
			case "Etisalat":
				if (s.getOperatorPackageId() > 0) {
					newService = serviceRepository.save(s);
				}
				break;
			case "Orange":
				if (s.getOperatorServiceId() > 0) {
					newService = serviceRepository.save(s);
				}
				break;
			default:
				newService = serviceRepository.save(s);
				break;
			}
		}
		if (viableForUpdate) {
			newService = serviceRepository.save(s);
		}
		return newService;
	}

	public Integer deleteServiceByTelecomServiceName(String name) {
		return serviceRepository.deleteByTelecomServiceName(name);
	}

	public List<TelecomService> getAllTelecomServices() {
		List<TelecomService> services = new ArrayList<>();
		serviceRepository.findAll().forEach(services::add);
		return services;
	}
}
