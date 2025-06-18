package com.example.configapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.configapi.model.ConfigConstant;

public interface ConfigConstantRepository extends JpaRepository<ConfigConstant, Long> {
	Optional<ConfigConstant> findByConstantNameAndEnvironment(String constantName, String environment);
}
