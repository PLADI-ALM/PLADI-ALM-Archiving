package com.example.PLADIALMArchiving.archiving.entity;

import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum Category {

  GENERAL("전체"),
  IMAGE("이미지"),
  VIDEO("비디오"),
  DOCS("문서");

  private final String value;

  public static Category getCategoryByValue(String value) {
    return Arrays.stream(Category.values())
            .filter(r -> r.getValue().equals(value))
            .findAny().orElseThrow(() -> new BaseException(BaseResponseCode.CATEGORY_NOT_FOUND));
  }
}
