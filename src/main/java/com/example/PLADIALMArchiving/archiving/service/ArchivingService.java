package com.example.PLADIALMArchiving.archiving.service;

import com.example.PLADIALMArchiving.archiving.repository.MaterialRepository;
import com.example.PLADIALMArchiving.archiving.repository.ProjectRepository;
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


}
