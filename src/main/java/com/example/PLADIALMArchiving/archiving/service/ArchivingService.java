package com.example.PLADIALMArchiving.archiving.service;

import com.example.PLADIALMArchiving.archiving.dto.request.RegisterProjectReq;
import com.example.PLADIALMArchiving.archiving.entity.Project;
import com.example.PLADIALMArchiving.archiving.repository.MaterialRepository;
import com.example.PLADIALMArchiving.archiving.repository.ProjectRepository;
import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
import com.example.PLADIALMArchiving.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
