package com.example.capgemini.controller;

import static java.util.Arrays.asList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.example.capgemini.domain.Project;


@Controller
@RequestMapping("/gui/projects")
@Transactional
public class ProjectControllerGUI extends ProjectController {
	@GetMapping()
	public String projects(Model model) {
		model.addAttribute("projects", projectRepo.findAll());
		return "projects";
	}

	@GetMapping("/{id}")
	public String project(@PathVariable Long id, Model model) {
		Project project = projectRepo.findById(id).get();
		model.addAttribute("projects", List.of(project));
		return "projects";
	}

	@PostMapping()
	public String create(@RequestParam String name, Model model) {
		//		long time = System.currentTimeMillis();
		createProject(name);

		//		Project project = new Project(name);
		//		projectRepo.save(project);
		//		List<Geometry> geometryList = new ArrayList<>();
		//		List<Attribute> attributeList = new ArrayList<>();
		//		for (int i = 0; i < qtyObjs; i++) {
		//			geometryList.add(new Geometry("Geom" + i, project));
		//		}
		//		geometryRepo.saveAll(geometryList);
		//		for (int i = 0; i < qtyObjs; i++) {
		//			attributeList.add(
		//					new Attribute("Attr" + i, project, geometryList.get(random.nextInt(qtyObjs))));
		//		}
		//		attributeRepo.saveAll(attributeList);
		model.addAttribute("projects", projectRepo.findAll());
		//		System.out.println("creation finish !!!!!");
		//		System.out.println(System.currentTimeMillis() - time);
		return "projects";
	}

	@PutMapping("/{id}")
	public String rename(@PathVariable Long id, @RequestParam String newName, Model model) {
		renameProject(id, newName);
		model.addAttribute("projects", projectRepo.findAll());
		return "projects";
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, Model model) {
		deleteProject(id);
		model.addAttribute("projects", projectRepo.findAll());
		return "projects";
	}

	public Project[] getForEntity() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Project[]> response = restTemplate
				.getForEntity("http://localhost:8080/api/projects/", Project[].class);
		Project[] employees = response.getBody();
		asList(employees).forEach(System.out::println);
		return employees;
	}
}
