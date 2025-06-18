package com.example.configapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.configapi.model.ConfigConstant;
import com.example.configapi.repository.ConfigConstantRepository;

@Service
public class ConfigConstantService {

	@Autowired
	private ConfigConstantRepository repository;

	public List<ConfigConstant> getAll() {
		return repository.findAll();
	}

	public ConfigConstant getById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
	}

	public ConfigConstant create(ConfigConstant constant) {
		return repository.save(constant);
	}

	public ConfigConstant getByNameAndEnvironment(String name, String environment) {
		return repository.findByConstantNameAndEnvironment(name, environment).orElseThrow(() -> new RuntimeException(
				"Configuration not found for name: " + name + " and environment: " + environment));
	}

	public ConfigConstant update(Long id, ConfigConstant updated) {
		ConfigConstant existing = getById(id);
		existing.setConstantName(updated.getConstantName());
		existing.setConstantValue(updated.getConstantValue());
		existing.setEnvironment(updated.getEnvironment());
		existing.setDescription(updated.getDescription());
		return repository.save(existing);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
