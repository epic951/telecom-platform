package com.epic951.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epic951.data.entities.TelecomService;
import com.epic951.data.repositories.TelecomServiceRepository;

@Service
public class TelecomServiceHandler {
	@Autowired
	private TelecomServiceRepository serviceRepository;

	public TelecomService addService(TelecomService s) {
		// Validation is required ..
		if (s.getTelecomService_name() != null && !s.getTelecomService_name().isEmpty()) {
			TelecomService newService = serviceRepository.save(s);
			return newService;
		}
		return null;
	}

	public List<TelecomService> getAllOperators() {
		List<TelecomService> services = new ArrayList<>();
		serviceRepository.findAll().forEach(services::add);
		return services;
	}
}
