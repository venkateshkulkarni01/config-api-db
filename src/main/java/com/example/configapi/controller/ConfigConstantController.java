package com.example.configapi.controller;

import com.example.configapi.model.ConfigConstant;
import com.example.configapi.service.ConfigConstantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/configurations")
public class ConfigConstantController {

	private final ConfigConstantService service;

	public ConfigConstantController(ConfigConstantService service) {
		this.service = service;
	}

	@GetMapping
    public List<ConfigConstant> getAll() {
        return service.getAll();
    }

    @GetMapping("/by-name-env")
    public ResponseEntity<ConfigConstant> getByNameAndEnv(@RequestParam String name, @RequestParam String env) {
        return service.getByNameAndEnvironment(name, env)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ConfigConstant> create(@RequestBody ConfigConstant prop) {
        return ResponseEntity.ok(service.create(prop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfigConstant> update(@PathVariable Long id, @RequestBody ConfigConstant prop) {
        return ResponseEntity.ok(service.update(id, prop));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
