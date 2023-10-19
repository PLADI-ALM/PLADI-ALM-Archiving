package com.example.PLADIALMArchiving.archiving.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class RegisterProjectReq {

  @Schema(type = "String", description = "프로젝트 이름 (공백, 특수문자 제외)", minLength = 1, maxLength = 20)
  @Size(max = 20, message = "P0006")
  private String name;
}
