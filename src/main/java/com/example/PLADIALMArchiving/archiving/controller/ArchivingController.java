package com.example.PLADIALMArchiving.archiving.controller;

import com.example.PLADIALMArchiving.archiving.dto.request.RegisterProjectReq;
import com.example.PLADIALMArchiving.archiving.service.ArchivingService;
import com.example.PLADIALMArchiving.global.response.ResponseCustom;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "아카이빙 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/archives")
public class ArchivingController {

  private final ArchivingService archivingService;

  /**
   * 프로젝트를 추가한다.
   */
  @PostMapping("/projects/register")
  public ResponseCustom<?> registerProject(@RequestBody RegisterProjectReq registerProjectReq) {
    archivingService.registerProject(registerProjectReq);
    return ResponseCustom.OK();
  }
  /**
   * 자료를 업로드한다.
   */

  /**
   * 자료목록을 조회 및 검색한다.
   */

  /**
   * 자료를 삭제한다.
   */

  /**
   * 자료를 다운로드한다.
   */
}
