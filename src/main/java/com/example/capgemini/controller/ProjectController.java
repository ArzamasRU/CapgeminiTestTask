package com.example.capgemini.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.capgemini.domain.Attribute;
import com.example.capgemini.domain.Geometry;
import com.example.capgemini.domain.Project;
import com.example.capgemini.repos.AttributeRepo;
import com.example.capgemini.repos.GeometryRepo;
import com.example.capgemini.repos.ProjectRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

abstract class ProjectController {
	Random random = new Random();
	int qtyObjs = 100_000;
	ObjectMapper mapper = new ObjectMapper();

	@Autowired
	ProjectRepo projectRepo;

	@Autowired
	GeometryRepo geometryRepo;

	@Autowired
	AttributeRepo attributeRepo;

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void createProject(String name) {
		List<Geometry> geometryList = new ArrayList<>();
		Geometry geometry;
		Project project = new Project(name);
		entityManager.persist(project);

		for (int i = 0; i < qtyObjs; i++) {
			if (i > 0 && i % 50 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
			geometry = new Geometry("Geom" + i, project);
			geometryList.add(geometry);
			entityManager.persist(geometry);
		}
		entityManager.flush();

		for (int i = 0; i < qtyObjs; i++) {
			if (i > 0 && i % 50 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
			entityManager.persist(
					new Attribute("Attr" + i, project, geometryList.get(random.nextInt(qtyObjs))));
		}
		entityManager.flush();
	}

	public void deleteProject(Long id) {
		Project project = projectRepo.findById(id).get();
		attributeRepo.deleteAttributeByProject(project);
		geometryRepo.deleteGeometryByProject(project);
		projectRepo.delete(project);
	}

	public void renameProject(Long id, String newName) {
		Project project = projectRepo.findById(id).get();
		project.setName(newName);
		projectRepo.save(project);
	}
}
