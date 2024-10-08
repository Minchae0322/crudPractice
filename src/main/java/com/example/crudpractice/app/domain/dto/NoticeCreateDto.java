package com.example.crudpractice.app.domain.dto;


import com.example.crudpractice.app.domain.Notice;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateDto {

    //todo
    @NotBlank
    @Schema(description = "공지사항 제목")
    private String noticeTitle;

    @NotBlank
    @Schema(description = "공지사항 컨텐트")
    private String noticeContent;


}
