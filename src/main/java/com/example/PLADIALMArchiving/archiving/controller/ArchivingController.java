package com.example.PLADIALMArchiving.archiving.controller;

import com.example.PLADIALMArchiving.archiving.dto.request.RegisterProjectReq;
import com.example.PLADIALMArchiving.archiving.dto.request.SearchMaterialReq;
import com.example.PLADIALMArchiving.archiving.dto.request.UploadMaterialReq;
import com.example.PLADIALMArchiving.archiving.service.ArchivingService;
import com.example.PLADIALMArchiving.global.resolver.Account;
import com.example.PLADIALMArchiving.global.response.ResponseCustom;
import com.example.PLADIALMArchiving.user.entity.User;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

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
  @PostMapping("/projects/{projectId}/upload")
  public ResponseCustom<?> uploadMaterial(
          @RequestBody UploadMaterialReq uploadMaterialReq,
          @PathVariable Long projectId,
          @Account User user
  )
  {
    archivingService.uploadMaterial(uploadMaterialReq, projectId, user);
    return ResponseCustom.OK();
  }

  /**
   * 자료목록을 조회 및 검색한다.
   */
  @GetMapping("/projects/{projectId}")
  public ResponseCustom<?> searchMaterial(
          @PathVariable Long projectId,
          @RequestBody SearchMaterialReq searchMaterialReq,
          Pageable pageable
  )
  {
    return ResponseCustom.OK(archivingService.searchMaterial(projectId, searchMaterialReq, pageable));
  }
  /**
   * 자료를 삭제한다.
   */

  /**
   * 자료를 다운로드한다.
   */
}
