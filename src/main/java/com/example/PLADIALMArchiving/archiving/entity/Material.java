package com.example.PLADIALMArchiving.archiving.entity;

import com.example.PLADIALMArchiving.archiving.dto.request.UploadMaterialReq;
import com.example.PLADIALMArchiving.global.entity.BaseEntity;
import com.example.PLADIALMArchiving.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@DynamicUpdate
@Where(clause = "is_enable = true")
public class Material extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long materialId;

  private String name;

  private String extension;

  private Long size;

  private String fileKey;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "project_id")
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  @Builder
  public Material(String name, String extension, Long size, String fileKey, Project project, User user) {
    this.name = name;
    this.extension = extension;
    this.size = size;
    this.fileKey = fileKey;
    this.project = project;
    this.user = user;
  }

  public static Material toEntity(UploadMaterialReq uploadMaterialReq, Project project, User user) {
    return Material.builder()
            .name(uploadMaterialReq.getName())
            .extension(uploadMaterialReq.getExtension())
            .size(uploadMaterialReq.getSize())
            .fileKey(uploadMaterialReq.getFileKey())
            .project(project)
            .user(user)
            .build();
  }

  public void update(String name) {
    this.name = name;
  }
}
