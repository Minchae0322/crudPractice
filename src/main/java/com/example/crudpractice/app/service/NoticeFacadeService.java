package com.example.crudpractice.app.service;

import com.example.crudpractice.app.domain.dto.NoticeCreateDto;
import com.example.crudpractice.app.domain.dto.NoticeDto;
import com.example.crudpractice.app.domain.dto.NoticeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NoticeFacadeService {

    private final NoticeService noticeService;



    public Long create(NoticeCreateDto createDto) {
        return noticeService.create(createDto);
    }

    public NoticeDto getBasicNotice(Long noticeId) {
        return noticeService.getBasicNotice(noticeId);
    }

    public NoticeDto update(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        return noticeService.updateNotice(noticeId, noticeUpdateDto);
    }
}
