package com.example.crudpractice.app.domain.dto;


import com.example.crudpractice.app.domain.Notice;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeCreateDto {

    @NotBlank
    @Max(120)
    @Schema(description = "공지사항 제목")
    private String noticeTitle;

    @NotBlank
    @Schema(description = "공지사항 컨텐트")
    private String noticeContent;

    @NotBlank
    @Schema(description = "공지사항 상단 고정 상태")
    private boolean isTop;

    public Notice toEntity() {
        return Notice.builder()
                .title(this.noticeTitle)
                .content(this.noticeContent)
                .isEnabled(true)
                .isTop(isTop)
                .build();
    }

}
