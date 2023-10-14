package com.example.PLADIALMArchiving.archiving.repository;

import com.example.PLADIALMArchiving.archiving.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  Optional<Project> findByName(String name);
}
