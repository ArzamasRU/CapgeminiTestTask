package com.example.capgemini.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/")
	public String greeting(Map<String, Object> model) {
		return "greeting";
	}
}
