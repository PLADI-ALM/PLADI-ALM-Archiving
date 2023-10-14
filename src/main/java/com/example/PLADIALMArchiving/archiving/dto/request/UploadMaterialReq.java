package com.example.PLADIALMArchiving.archiving.dto.request;

import lombok.Getter;

@Getter
public class UploadMaterialReq {
  private String fileKey;
  private String name;
  private String extension;
}
