package com.example.PLADIALMArchiving.archiving.repository;

import com.example.PLADIALMArchiving.archiving.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
