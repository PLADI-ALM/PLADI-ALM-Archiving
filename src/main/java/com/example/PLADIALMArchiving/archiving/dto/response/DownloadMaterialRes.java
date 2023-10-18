package com.example.PLADIALMArchiving.archiving.dto.response;

import com.example.PLADIALMArchiving.global.utils.AwsS3FileUrlUtil;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DownloadMaterialRes {
  private String awsS3FileUrl;

  @Builder
  public DownloadMaterialRes(String awsS3FileUrl) {
    this.awsS3FileUrl = awsS3FileUrl;
  }

  public static DownloadMaterialRes toDto(String fileKey) {
    return DownloadMaterialRes.builder()
            .awsS3FileUrl(AwsS3FileUrlUtil.toUrl(fileKey))
            .build();
  }
}
