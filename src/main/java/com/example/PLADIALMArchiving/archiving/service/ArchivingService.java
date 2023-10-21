package com.example.PLADIALMArchiving.archiving.service;

import com.example.PLADIALMArchiving.archiving.dto.request.*;
import com.example.PLADIALMArchiving.archiving.dto.response.DownloadMaterialRes;
import com.example.PLADIALMArchiving.archiving.dto.response.SearchMaterialRes;
import com.example.PLADIALMArchiving.archiving.dto.response.SearchProjectRes;
import com.example.PLADIALMArchiving.archiving.entity.Category;
import com.example.PLADIALMArchiving.archiving.entity.Material;
import com.example.PLADIALMArchiving.archiving.entity.Project;
import com.example.PLADIALMArchiving.archiving.repository.MaterialRepository;
import com.example.PLADIALMArchiving.archiving.repository.ProjectRepository;
import com.example.PLADIALMArchiving.global.Constants;
import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
import com.example.PLADIALMArchiving.user.entity.Role;
import com.example.PLADIALMArchiving.user.entity.User;
import com.example.PLADIALMArchiving.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArchivingService {

  private final ProjectRepository projectRepository;
  private final MaterialRepository materialRepository;


  @Transactional
  public void registerProject(RegisterProjectReq registerProjectReq) {
    if(!validatePrjNameInput(registerProjectReq.getName())) throw new BaseException(BaseResponseCode.INVALID_NAME);
    boolean present = projectRepository.findByName(registerProjectReq.getName()).isPresent();
    if(present) throw new BaseException(BaseResponseCode.ALREADY_REGISTERED_PROJECT);
    projectRepository.save(Project.toEntity(registerProjectReq.getName()));
  }

  private boolean validatePrjNameInput(String name) {
    return name.matches("^[a-zA-Z0-9가-핳]{1,20}$");
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

    materialRepository.delete(material);
  }

  public Page<SearchMaterialRes> searchMaterial(Long projectId, SearchMaterialReq searchMaterialReq, Pageable pageable) {
    Project project = projectRepository.findById(projectId).orElseThrow(() -> new BaseException(BaseResponseCode.PROJECT_NOT_FOUND));
    Category category = Category.getCategoryByValue(searchMaterialReq.getCategory());

    Page<Material> filteredMaterials;

    if (category != Category.GENERAL) {
      List<String> extension = new ArrayList<>();
      if (category == Category.IMAGE) {
        extension = List.of(Constants.EXTENSION.IMAGE.split(" "));
      } else if (category == Category.VIDEO) {
        extension = List.of(Constants.EXTENSION.VIDEO.split(" "));
      } else if (category == Category.DOCS) {
        extension = List.of(Constants.EXTENSION.DOCS.split(" "));
      }
      filteredMaterials = materialRepository.findByProjectAndExtensionInAndNameContaining(project, extension, searchMaterialReq.getCond(), pageable);
    } else {
      filteredMaterials = materialRepository.findAll(pageable);
    }

    return filteredMaterials.map(SearchMaterialRes::toDto);
  }

  public DownloadMaterialRes downloadMaterial(Long materialsId) {
    Material material = materialRepository.findById(materialsId).orElseThrow(() -> new BaseException(BaseResponseCode.MATERIAL_NOT_FOUND));
    return DownloadMaterialRes.toDto(material.getFileKey());
  }

  @Transactional
  public void updateMaterial(Long materialId, UpdateMaterialReq updateMaterialReq) {
    Material material = materialRepository.findById(materialId).orElseThrow(() -> new BaseException(BaseResponseCode.MATERIAL_NOT_FOUND));
    material.update(updateMaterialReq.getName());
  }

  public Page<SearchProjectRes> searchProject(Pageable pageable) {
    Page<Project> all = projectRepository.findAll(pageable);
    return all.map(SearchProjectRes::toDto);
  }

  @Transactional
  public void updateProject(Long projectId, UpdateProjectReq updateProjectReq) {
    Project project = projectRepository.findById(projectId).orElseThrow(() -> new BaseException(BaseResponseCode.PROJECT_NOT_FOUND));
    project.update(updateProjectReq.getName());
  }
}
