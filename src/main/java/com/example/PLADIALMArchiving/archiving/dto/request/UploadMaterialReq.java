package com.example.PLADIALMArchiving.archiving.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UploadMaterialReq {

  @Schema(type = "String", description = "aws s3 file key")
  @NotNull(message = "P0007")
  private String fileKey;

  @Schema(type = "String", description = "자료 이름 (공백, 특수문자 제외)", minLength = 1, maxLength = 20)
  @Size(max = 20, message = "P0007")
  private String name;

  @Schema(type = "String", description = "자료 사이즈")
  @NotNull(message = "P0007")
  private Long size;

  @Schema(type = "String", description = "확장자 ex) png, jpeg, mp4 ...")
  @NotNull(message = "P0007")
  private String extension;
}
