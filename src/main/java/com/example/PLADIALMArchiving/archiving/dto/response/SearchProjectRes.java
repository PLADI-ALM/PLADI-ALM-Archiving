package com.example.PLADIALMArchiving.archiving.dto.response;

import com.example.PLADIALMArchiving.archiving.entity.Project;
import com.example.PLADIALMArchiving.global.Constants;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class SearchProjectRes {
  private Long projectId;
  private String projectName;
  private String createdAt;

  @Builder
  public SearchProjectRes(Long projectId, String projectName, String createdAt) {
    this.projectId = projectId;
    this.projectName = projectName;
    this.createdAt = createdAt;
  }

  public static SearchProjectRes toDto(Project project) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
    return SearchProjectRes.builder()
            .projectId(project.getProjectId())
            .projectName(project.getName())
            .createdAt(project.getCreatedAt().format(dateTimeFormatter))
            .build();
  }
}
