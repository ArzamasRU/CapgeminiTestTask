package com.example.capgemini.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.capgemini.domain.Attribute;
import com.example.capgemini.domain.Project;

public interface AttributeRepo extends JpaRepository<Attribute, Long> {
	List<Attribute> findByProject(Project project);
	@Modifying
	@Transactional
	@Query("DELETE FROM Attribute where project = :project")
	void deleteAttributeByProject(Project project);
}
