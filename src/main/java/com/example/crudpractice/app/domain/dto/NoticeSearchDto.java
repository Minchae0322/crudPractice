package com.example.crudpractice.app.domain.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeSearchDto {
    String keyword;

    String keywordTarget;

    boolean isEnabled;

}
