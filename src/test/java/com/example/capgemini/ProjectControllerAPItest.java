package com.example.capgemini;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.capgemini.repos.ProjectRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerAPItest {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProjectRepo projectRepo;

	@Test
	public void contextLoads() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("This is a test task for capgemini")));
	}

	@Test
	public void apiControllerShouldReturnVisits() throws Exception {
		int projectAmount = projectRepo.findAll().size();
		mockMvc.perform(get("/api/projects"))
				.andExpect(jsonPath("$.*.name", iterableWithSize(projectAmount)));
	}
}
