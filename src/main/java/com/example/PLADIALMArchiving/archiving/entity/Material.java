package com.example.PLADIALMArchiving.archiving.entity;

import com.example.PLADIALMArchiving.global.entity.BaseEntity;
import com.example.PLADIALMArchiving.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_enable = true")
public class Material extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long materialId;

  private String name;

  private String extension;

  private Long size;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "project_id")
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;
}
