package com.example.configapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.configapi.model.ConfigConstant;
import com.example.configapi.repository.ConfigConstantRepository;

class ConfigConstantServiceTest {

	@InjectMocks
	private ConfigConstantService service;

	@Mock
	private ConfigConstantRepository repository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testGetAll() {
		List<ConfigConstant> list = List
				.of(new ConfigConstant(1L, "key1", "value1", "dev", "desc", LocalDateTime.now(), LocalDateTime.now()));
		when(repository.findAll()).thenReturn(list);
		assertEquals(1, service.getAll().size());
	}

	@Test
	void testGetById() {
		ConfigConstant config = new ConfigConstant(1L, "key1", "value1", "dev", "desc", LocalDateTime.now(),
				LocalDateTime.now());
		when(repository.findById(1L)).thenReturn(Optional.of(config));
		Optional<ConfigConstant> result = service.getById(1L);
		assertTrue(result.isPresent());
		assertEquals("key1", result.get().getConstantName());
	}

	@Test
	void testCreate() {
		ConfigConstant input = new ConfigConstant(null, "key1", "val", "dev", "desc", null, null);
		ConfigConstant saved = new ConfigConstant(1L, "key1", "val", "dev", "desc", LocalDateTime.now(),
				LocalDateTime.now());
		when(repository.save(any())).thenReturn(saved);
		ConfigConstant result = service.create(input);
		assertNotNull(result.getId());
	}

	@Test
	void testUpdate() {
		ConfigConstant existing = new ConfigConstant(1L, "key1", "val", "dev", "desc", LocalDateTime.now(),
				LocalDateTime.now());
		ConfigConstant update = new ConfigConstant(null, "key1", "newVal", "dev", "desc2", null, null);
		when(repository.findById(1L)).thenReturn(Optional.of(existing));
		when(repository.save(any())).thenReturn(existing);

		ConfigConstant result = service.update(1L, update);
		assertEquals("newVal", result.getConstantValue());
		assertEquals("desc2", result.getDescription());
	}

	@Test
	void testDelete() {
		service.delete(1L);
		verify(repository, times(1)).deleteById(1L);
	}
}
