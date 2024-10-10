package com.example.crudpractice.app.controller;

import com.example.crudpractice.app.core.response.ApiResponse;
import com.example.crudpractice.app.domain.dto.NoticeCreateDto;
import com.example.crudpractice.app.domain.dto.NoticeDto;
import com.example.crudpractice.app.domain.dto.NoticeUpdateDto;
import com.example.crudpractice.app.service.NoticeFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.example.crudpractice.app.core.util.PageInfoConstant.PAGE_SIZE;


@Tag(name = "Notice", description = "공지사항 API")
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping("/notice")
@RestController
public class NoticeController {

    private final NoticeFacadeService noticeFacadeService;

    @Operation(summary = "공지사항 등록", tags = "Notice")
    @PostMapping(path = "")
    public ResponseEntity<?> create(
            @RequestBody NoticeCreateDto createDto
    ){
        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.create(createDto)), HttpStatus.OK);
    }

    @Operation(summary = "공지사항 상세 조회", tags = "Notice")
    @GetMapping(path = "/{noticeId}")
    public ResponseEntity<?> findNotice(
            @Parameter(name = "noticeId", description = "공지사항 ID") @PathVariable(value = "noticeId") Long noticeId,
            @Parameter(name = "userMngId", description = "공지사항 조회한 유저 ID") @RequestParam(value = "user_mng_id") Long userMngId
    ){
        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.findNotice(noticeId, userMngId)), HttpStatus.OK);
    }



    @Operation(summary = "공지사항 수정", tags = "Notice")
    @PatchMapping(path = "/update/{noticeId}")
    public ResponseEntity<?> update(
            @Parameter(name = "noticeId", description = "공지사항 ID") @PathVariable Long noticeId,
            @RequestBody NoticeUpdateDto updateDto
    ){

        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.update(noticeId, updateDto)), HttpStatus.OK);
    }

    @Operation(summary = "상단 공지 사항 부터 페이지로 불러오기", tags = "Notice")
    @PostMapping(path = "/list")
    public ResponseEntity<Page<NoticeDto>> getNoticePagePriorityTopNotice(
            @Parameter(hidden = true) @PageableDefault(size = PAGE_SIZE, direction = Sort.Direction.DESC) Pageable pageable
    ){
        return new ResponseEntity<>(noticeFacadeService.getNoticePagePriorityTopNotice(pageable), HttpStatus.OK);
    }

    @Operation(summary = "해당 날짜 공지사항 불러오기", tags = "Notice")
    @PostMapping(path = "/list/date")
    public ResponseEntity<Page<NoticeDto>> getNoticePagePriorityTopNotice(
            @Parameter(hidden = true) @PageableDefault(size = PAGE_SIZE, direction = Sort.Direction.DESC) Pageable pageable,
            @Parameter(name = "noticeCreateDate", description = "공지사항 생성 일자")@RequestParam(value = "noticeCreateDate") LocalDate noticeCreateDate
    ){
        return new ResponseEntity<>(noticeFacadeService.findNoticePageByDateYYYYMMDD(pageable, noticeCreateDate, true), HttpStatus.OK);
    }

    @Operation(summary = "공지사항 상태만 삭제", tags = "Notice")
    @PatchMapping(path = "/delete/{noticeId}")
    public ResponseEntity<?> delete(
            @Parameter(name = "noticeId", description = "공지사항 ID") @PathVariable Long noticeId
    ){
        noticeFacadeService.deleteNotice(noticeId);
        return new ResponseEntity<>(new ApiResponse<>("delete success"), HttpStatus.OK);
    }


    @Operation(summary = "공지사항 상단고정 상태 변경", tags = "Notice")
    @PatchMapping(path = "/fix/{noticeId}")
    public ResponseEntity<Long> updateNoticeFixStatus(
            @Parameter(name = "noticeId", description = "공지사항 ID") @PathVariable Long noticeId,
            @Parameter(name = "isTop", description = "상단 고정 여부") @RequestParam(value = "isTop") Boolean isTop
    ){
        return new ResponseEntity<>(noticeFacadeService.updateNoticeStatus(noticeId, isTop), HttpStatus.OK);
    }
}
