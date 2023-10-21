package com.example.PLADIALMArchiving.archiving.controller;

import com.example.PLADIALMArchiving.archiving.dto.request.*;
import com.example.PLADIALMArchiving.archiving.dto.response.DownloadMaterialRes;
import com.example.PLADIALMArchiving.archiving.dto.response.SearchMaterialRes;
import com.example.PLADIALMArchiving.archiving.dto.response.SearchProjectRes;
import com.example.PLADIALMArchiving.archiving.service.ArchivingService;
import com.example.PLADIALMArchiving.global.exception.BaseException;
import com.example.PLADIALMArchiving.global.exception.BaseResponseCode;
import com.example.PLADIALMArchiving.global.resolver.Account;
import com.example.PLADIALMArchiving.global.response.ResponseCustom;
import com.example.PLADIALMArchiving.user.entity.User;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "아카이빙 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/archives")
public class ArchivingController {

  private final ArchivingService archivingService;

  /**
   * 프로젝트를 추가한다.
   */
  @Operation(summary = "프로젝트 추가 (김민기)", description = "자료 아카이빙을 위한 프로젝트를 추가한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "409", description = "(P0001)이미 등록된 프로젝트입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "400", description = "(P0006)올바르지 않은 프로젝트 이름입니다. 다시 입력해주세요. (공백, 특수문자 제외 20자 이내)", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @PostMapping("/projects")
  public ResponseCustom<?> registerProject(@RequestBody @Valid RegisterProjectReq registerProjectReq)
  {
    archivingService.registerProject(registerProjectReq);
    return ResponseCustom.OK();
  }

  /**
   * 자료를 업로드한다.
   */
  @Operation(summary = "자료 업로드 (김민기)", description = "아카이빙할 자료를 업로드한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "400", description = "(G0001)잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "403", description = "(G0002)접근권한이 없습니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(P0002)존재하지 않는 프로젝트입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @PostMapping("/projects/{projectId}")
  public ResponseCustom<?> uploadMaterial
  (
          @RequestBody @Valid UploadMaterialReq uploadMaterialReq,
          @Parameter(description = "(Long) 프로젝트 Id", example = "1") @PathVariable(name = "projectId") Long projectId,
          @Account User user
  )
  {
    if(!StringUtils.hasText(uploadMaterialReq.getFileKey()) || !StringUtils.hasText(uploadMaterialReq.getName()) ||
            !StringUtils.hasText(uploadMaterialReq.getExtension()) || uploadMaterialReq.getSize() == null)
      throw new BaseException(BaseResponseCode.INVALID_UPLOAD_MATERIAL_REQUEST);

    archivingService.uploadMaterial(uploadMaterialReq, projectId, user);
    return ResponseCustom.OK();
  }

  /**
   * 자료목록을 조회 및 검색한다.
   */
  // todo 이름, 게시일 오름차순 내림차순 정렬 구현 필요
  @Operation(summary = "자료목록 조회 및 검색 (김민기)", description = "아카이빙 자료목록을 조회 및 검색한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "404", description = "(P0002)존재하지 않는 프로젝트입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(P0003)존재하지 않는 카테고리입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @GetMapping("/projects/{projectId}")
  public ResponseCustom<Page<SearchMaterialRes>> searchMaterial
  (
          @Parameter(description = "(Long) 프로젝트 Id", example = "1") @PathVariable(name = "projectId") Long projectId,
          @RequestBody @Valid SearchMaterialReq searchMaterialReq,
          Pageable pageable
  )
  {
    return ResponseCustom.OK(archivingService.searchMaterial(projectId, searchMaterialReq, pageable));
  }
  /**
   * 자료를 삭제한다.
   */
  @Operation(summary = "자료 삭제 (김민기)", description = "아카이빙 자료를 삭제한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "400", description = "(G0001)잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "403", description = "(G0002)접근권한이 없습니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(P0004)존재하지 않는 자료입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "401", description = "(P0005)관리자 계정 또는 자료를 업로드한 유저가 아니므로 자료를 삭제할 수 없습니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @DeleteMapping("/materials/{materialId}")
  public ResponseCustom<?> deleteMaterial(
          @Parameter(description = "(Long) 자원 Id", example = "15") @PathVariable(name = "materialId") Long materialId,
          @Account User user
  )
  {
    archivingService.deleteMaterial(materialId, user);
    return ResponseCustom.OK();
  }

  /**
   * 자료를 다운로드한다.
   */
  @Operation(summary = "자료 다운로드 (김민기)", description = "아카이빙 자료를 다운로드한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "400", description = "(G0001)잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(P0004)존재하지 않는 자료입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @GetMapping("/materials/{materialId}")
  public ResponseCustom<DownloadMaterialRes> downloadMaterial(
          @Parameter(description = "(Long) 자원 Id", example = "15") @PathVariable(name = "materialId") Long materialId
  ) {
    return ResponseCustom.OK(archivingService.downloadMaterial(materialId));
  }

  /**
   * 자료 파일명을 변경한다.
   */
  @Operation(summary = "자료 파일명 변경 (김민기)", description = "아카이빙 자료 파일명을 변경한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "400", description = "(G0001)잘못된 요청입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
          @ApiResponse(responseCode = "404", description = "(P0004)존재하지 않는 자료입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class))),
  })
  @PatchMapping("/materials/{materialId}")
  public ResponseCustom<?> updateMaterial(
          @Parameter(description = "(Long) 자원 Id", example = "15") @PathVariable(name = "materialId") Long materialId,
          @RequestBody @Valid UpdateMaterialReq updateMaterialReq
  )
  {
    archivingService.updateMaterial(materialId, updateMaterialReq);
    return ResponseCustom.OK();
  }

  /**
   * 프로젝트 목록을 조회한다.
   */
  @Operation(summary = "프로젝트 목록 조회 (김민기)", description = "아카이빙 프로젝트 목록을 조회한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
  })
  @GetMapping("/projects")
  public ResponseCustom<Page<SearchProjectRes>> searchProject(
          Pageable pageable
  )
  {
    return ResponseCustom.OK(archivingService.searchProject(pageable));
  }

  /**
   * 프로젝트 이름을 변경한다.
   */
  @Operation(summary = "프로젝트 이름 변경 (김민기)", description = "아카이빙 프로젝트 이름을 변경한다.")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "(S0001)요청에 성공했습니다."),
          @ApiResponse(responseCode = "404", description = "(P0002)존재하지 않는 프로젝트입니다.", content = @Content(schema = @Schema(implementation = ResponseCustom.class)))
  })
  @PatchMapping("/projects/{projectId}")
  public ResponseCustom<?> updateProject(
          @Parameter(description = "(Long) 프로젝트 Id", example = "1")  @PathVariable Long projectId,
          @RequestBody @Valid UpdateProjectReq updateProjectReq
  )
  {
    archivingService.updateProject(projectId, updateProjectReq);
    return ResponseCustom.OK();
  }
}
