package com.example.configapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.configapi.model.ConfigConstant;
import com.example.configapi.service.ConfigConstantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ConfigConstantController.class)
class ConfigPropertyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConfigConstantService service;

	@Autowired
	private ObjectMapper objectMapper;

	private ConfigConstant config;

	@BeforeEach
	void setUp() {
		config = new ConfigConstant(1L, "key1", "value1", "dev", "desc", LocalDateTime.now(), LocalDateTime.now());
	}

	@Test
    void testGetAll() throws Exception {
        when(service.getAll()).thenReturn(List.of(config));
        mockMvc.perform(get("/configurations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].constantName").value("key1"));
    }

	@Test
    void testGetByNameAndEnv() throws Exception {
        when(service.getByNameAndEnvironment("key1", "dev")).thenReturn(Optional.of(config));
        mockMvc.perform(get("/configurations/by-name-env")
                        .param("name", "key1")
                        .param("env", "dev"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constantValue").value("value1"));
    }

	@Test
    void testCreate() throws Exception {
        when(service.create(any())).thenReturn(config);
        mockMvc.perform(post("/configurations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constantName").value("key1"));
    }

	@Test
    void testUpdate() throws Exception {
        when(service.update(eq(1L), any())).thenReturn(config);
        mockMvc.perform(put("/configurations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(config)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.constantName").value("key1"));
    }

	@Test
	void testDelete() throws Exception {
		doNothing().when(service).delete(1L);
		mockMvc.perform(delete("/configurations/1")).andExpect(status().isNoContent());
	}
}
