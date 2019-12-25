package com.example.capgemini.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.capgemini.domain.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
