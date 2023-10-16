package com.example.PLADIALMArchiving.global.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AwsS3FileUrlUtil {

  public static String bucket;
  public static String region;

  @Value("${aws.s3.region}")
  public void setRegion(String value) {
    region = value;
  }

  @Value("${aws.s3.bucket}")
  public void setBucket(String value) {
    bucket = value;
  }

  public static String toUrl(String fileKey) {
    return "https://"+bucket+".s3."+region+".amazonaws.com/"+fileKey;
  }
}
