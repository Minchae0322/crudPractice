package com.example.crudpractice.app.controller;

import com.example.crudpractice.app.core.response.ApiResponse;
import com.example.crudpractice.app.domain.dto.NoticeCreateDto;
import com.example.crudpractice.app.service.NoticeFacadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return new ResponseEntity<>(new ApiResponse<>(noticeFacadeService.getBasicNotice(createdId)), HttpStatus.OK);
    }


}
