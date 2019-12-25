package com.example.capgemini.controller;

import javax.transaction.Transactional;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;


@RestController
@RequestMapping("/api/projects")
@Transactional
public class ProjectControllerAPI extends ProjectController {
	@GetMapping()
	public String projects(Model model) throws JsonProcessingException {
		return mapper.writeValueAsString(projectRepo.findAll());
	}

	@GetMapping("/{id}")
	public String project(@PathVariable Long id) throws JsonProcessingException {
		return mapper.writeValueAsString(projectRepo.findById(id).get());
	}

	@PostMapping()
	public void create(@RequestParam String name) {
		createProject(name);
	}

	@PutMapping("/{id}")
	public void rename(@PathVariable Long id, @RequestBody String newName) {
		renameProject(id, newName);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		deleteProject(id);
	}
}
