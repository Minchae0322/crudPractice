package com.example.crudpractice.app.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeUpdateDto {

    private String noticeTitle;

    private String noticeContent;

    private boolean isTop;


}
