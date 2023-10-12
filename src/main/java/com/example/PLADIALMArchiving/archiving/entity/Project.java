package com.example.PLADIALMArchiving.archiving.entity;

import com.example.PLADIALMArchiving.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "is_enable = true")
public class Project extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long projectId;

  @NotNull
  @Size(max = 50)
  private String name;

  @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
  private List<Material> materialList = new ArrayList<>();
}
