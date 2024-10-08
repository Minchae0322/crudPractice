package com.example.crudpractice.app.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PageRequestDto {

    int page;
    int size;


    @Builder
    public PageRequestDto(int page, int size) {
        this.page = page;
        this.size = size;
    }
}
