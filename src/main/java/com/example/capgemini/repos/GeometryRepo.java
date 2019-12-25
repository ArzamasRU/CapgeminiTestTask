package com.example.capgemini.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.capgemini.domain.Geometry;
import com.example.capgemini.domain.Project;

public interface GeometryRepo extends JpaRepository<Geometry, Long> {
	List<Geometry> findByProject(Project project);
	@Modifying
	@Transactional
	@Query("DELETE FROM Geometry where project = :project")
	void deleteGeometryByProject(Project project);
}