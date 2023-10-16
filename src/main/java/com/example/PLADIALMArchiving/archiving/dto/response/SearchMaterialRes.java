package com.example.PLADIALMArchiving.archiving.dto.response;

import com.example.PLADIALMArchiving.archiving.entity.Material;
import com.example.PLADIALMArchiving.global.Constants;
import lombok.Builder;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
@Builder
public class SearchMaterialRes {
  private Long materialId;
  private String fileName;
  private String createdAt;
  private String publisher;
  private Long size;

  public static SearchMaterialRes toDto(Material material) {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
    return SearchMaterialRes.builder()
            .materialId(material.getMaterialId())
            .fileName(material.getName())
            .publisher(material.getUser().getName())
            .createdAt(material.getCreatedAt().format(dateTimeFormatter))
            .size(material.getSize())
            .build();
  }
}
