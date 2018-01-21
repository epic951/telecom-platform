package com.epic951.data.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.epic951.data.entities.TelecomService;

@Repository
@RepositoryRestResource(exported = false)
public interface TelecomServiceRepository extends CrudRepository<TelecomService, Long> {
	public Optional<TelecomService> findByTelecomServiceName(String telecomserviceName);

	public Optional<TelecomService> findByTelecomServiceId(int telecomServiceId);

	public Integer deleteByTelecomServiceName(String telecomServiceName);

	public Integer deleteByTelecomServiceId(int telecomServiceId);
}
