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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.time.LocalDate;

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
    public ResponseEntity<?> create(@RequestBody NoticeCreateDto createDto){
        Long createdId = noticeFacadeService.create(createDto);
        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.findNotice(createdId)), HttpStatus.OK);
    }

    @Operation(summary = "공지사항 상세 조회", tags = "Notice")
    @GetMapping(path = "notice/{noticeId}")
    public ResponseEntity<?> create(
            @Parameter(name = "noticeId", description = "공지사항 ID") @PathVariable(value = "noticeId") Long noticeId
    ){
        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.findNotice(noticeId)), HttpStatus.OK);
    }

    @Operation(summary = "공지사항 수정", tags = "Notice")
    @PatchMapping(path = "/notice/update/{noticeId}")
    public ResponseEntity<?> update(
            @PathVariable Long noticeId,
            @RequestBody NoticeUpdateDto updateDto
    ){

        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.update(noticeId, updateDto)), HttpStatus.OK);
    }

    @Operation(summary = "상단 공지 사항 부터 페이지로 불러오기", tags = "Notice")
    @PostMapping(path = "/notice/page=&size=?")
    public ResponseEntity<Page<NoticeDto>> getNoticePagePriorityTopNotice(
            Pageable pageable
    ){
        return new ResponseEntity<>(noticeFacadeService.getNoticePagePriorityTopNotice(pageable), HttpStatus.OK);
    }


    @Operation(summary = "공지사항 상태만 삭제", tags = "Notice")
    @PatchMapping(path = "/notice/delete/{noticeId}")
    public ResponseEntity<?> delete(
            @PathVariable Long noticeId
    ){
        noticeFacadeService.deleteNotice(noticeId);
        return new ResponseEntity<>(new ApiResponse<>("delete success"), HttpStatus.OK);
    }
}
