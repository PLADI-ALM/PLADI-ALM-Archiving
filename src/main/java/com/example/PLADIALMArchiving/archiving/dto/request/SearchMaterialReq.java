package com.example.PLADIALMArchiving.archiving.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class SearchMaterialReq {

  private String cond;

  @Schema(type = "String", description = "검색 카테고리 (전체, 이미지, 비디오, 문서)", maxLength = 10)
  @Size(max = 10, message = "P0003")
  private String category;
}
