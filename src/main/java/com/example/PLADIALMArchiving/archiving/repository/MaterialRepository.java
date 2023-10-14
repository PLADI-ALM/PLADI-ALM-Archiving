package com.example.PLADIALMArchiving.archiving.repository;

import com.example.PLADIALMArchiving.archiving.entity.Material;
import com.example.PLADIALMArchiving.archiving.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
  Page<Material> findByProjectAndExtensionInAndNameContaining(Project project, List<String> extensions, String cond, Pageable pageable);
}
