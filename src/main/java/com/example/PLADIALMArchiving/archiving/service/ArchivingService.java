package com.example.PLADIALMArchiving.archiving.service;

import com.example.PLADIALMArchiving.archiving.dto.request.RegisterProjectReq;
import com.example.PLADIALMArchiving.archiving.dto.request.SearchMaterialReq;
import com.example.PLADIALMArchiving.archiving.dto.request.UploadMaterialReq;
import com.example.PLADIALMArchiving.archiving.dto.response.SearchMaterialRes;
import com.example.PLADIALMArchiving.archiving.entity.Category;
import com.example.PLADIALMArchiving.archiving.entity.Material;
import com.example.PLADIALMArchiving.archiving.entity.Project;
import com.example.PLADIALMArchiving.archiving.repository.MaterialRepository;
import com.example.PLADIALMArchiving.archiving.repository.ProjectRepository;
import com.example.PLADIALMArchiving.global.Constants;
import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
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

}
