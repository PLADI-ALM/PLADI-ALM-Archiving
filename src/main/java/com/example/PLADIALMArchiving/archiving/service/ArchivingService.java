package com.example.PLADIALMArchiving.archiving.service;

import com.example.PLADIALMArchiving.archiving.dto.request.RegisterProjectReq;
import com.example.PLADIALMArchiving.archiving.dto.request.UploadMaterialReq;
import com.example.PLADIALMArchiving.archiving.entity.Material;
import com.example.PLADIALMArchiving.archiving.entity.Project;
import com.example.PLADIALMArchiving.archiving.repository.MaterialRepository;
import com.example.PLADIALMArchiving.archiving.repository.ProjectRepository;
import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
import com.example.PLADIALMArchiving.user.entity.Role;
import com.example.PLADIALMArchiving.user.entity.User;
import com.example.PLADIALMArchiving.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArchivingService {

  private final UserRepository userRepository;
  private final ProjectRepository projectRepository;
  private final MaterialRepository materialRepository;


  @Transactional
  public void registerProject(RegisterProjectReq registerProjectReq) {
    boolean present = projectRepository.findByName(registerProjectReq.getName()).isPresent();
    if(present) throw new BaseException(BaseResponseCode.ALREADY_REGISTERED_PROJECT);
    projectRepository.save(Project.toEntity(registerProjectReq.getName()));
  }

  @Transactional
  public void uploadMaterial(UploadMaterialReq uploadMaterialReq, Long projectId, User user) {
    Project project = projectRepository.findById(projectId).orElseThrow(() -> new BaseException(BaseResponseCode.PROJECT_NOT_FOUND));
    materialRepository.save(Material.toEntity(uploadMaterialReq, project, user));
  }

  @Transactional
  public void deleteMaterial(Long materialId, User user) {
    Material material = materialRepository.findById(materialId).orElseThrow(() -> new BaseException(BaseResponseCode.MATERIAL_NOT_FOUND));

    if(user.getRole() != Role.ADMIN || !user.getUserId().equals(material.getUser().getUserId()))
      throw new BaseException(BaseResponseCode.UNAUTHORIZED_USER);

    material.delete();
  }
}
