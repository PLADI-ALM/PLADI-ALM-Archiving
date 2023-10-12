package com.example.PLADIALMArchiving.archiving.repository;

import com.example.PLADIALMArchiving.archiving.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
