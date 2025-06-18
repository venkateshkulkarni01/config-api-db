package com.example.configapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.configapi.model.ConfigConstant;
import com.example.configapi.service.ConfigConstantService;

@RestController
@RequestMapping("/api/constants")
public class ConfigConstantController {

	@Autowired
	private ConfigConstantService service;

	@GetMapping
	public List<ConfigConstant> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ConfigConstant getById(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	public ConfigConstant create(@RequestBody ConfigConstant constant) {
		return service.create(constant);
	}

	@PutMapping("/{id}")
	public ConfigConstant update(@PathVariable Long id, @RequestBody ConfigConstant constant) {
		return service.update(id, constant);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/by-name-env")
	public ConfigConstant getByNameAndEnv(@RequestParam String name, @RequestParam String environment) {
		return service.getByNameAndEnvironment(name, environment);
	}
}
