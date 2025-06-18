package com.example.configapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.configapi.model.ConfigConstant;
import com.example.configapi.repository.ConfigConstantRepository;

@Service
public class ConfigConstantService {

	private final ConfigConstantRepository repository;

	public ConfigConstantService(ConfigConstantRepository repository) {
		this.repository = repository;
	}

	public List<ConfigConstant> getAll() {
		return repository.findAll();
	}

	public Optional<ConfigConstant> getById(Long id) {
		return repository.findById(id);
	}

	public Optional<ConfigConstant> getByNameAndEnvironment(String name, String env) {
		return repository.findByConstantNameAndEnvironment(name, env);
	}

	public ConfigConstant create(ConfigConstant prop) {
		prop.setCreatedAt(LocalDateTime.now());
		prop.setUpdatedAt(LocalDateTime.now());
		return repository.save(prop);
	}

	public ConfigConstant update(Long id, ConfigConstant updated) {
		return repository.findById(id).map(existing -> {
			existing.setConstantName(updated.getConstantName());
			existing.setConstantValue(updated.getConstantValue());
			existing.setEnvironment(updated.getEnvironment());
			existing.setDescription(updated.getDescription());
			existing.setUpdatedAt(LocalDateTime.now());
			return repository.save(existing);
		}).orElseThrow(() -> new RuntimeException("Configuration not found with ID: " + id));
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
