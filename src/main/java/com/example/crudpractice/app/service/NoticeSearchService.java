package com.example.crudpractice.app.service;


import com.example.crudpractice.app.domain.dto.NoticeDto;
import com.example.crudpractice.app.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional()
@RequiredArgsConstructor
@Service
public class NoticeSearchService {

    private final NoticeRepository noticeRepository;

    /**
     * 공지사항 등록 일자로 조회
     * @param pageable 페이지 offset, 페이지 size
     * @param noticeCreatedDate 공지사항 생성일자
     * @param isEnabled 공지사항 사용 가능 여부
     * @return 해당 날짜의 공지사항 페이지
     */
    public Page<NoticeDto> findNoticePageByDateYYYYMMDD(Pageable pageable, LocalDate noticeCreatedDate, boolean isEnabled) {
        return noticeRepository.findNoticePageByLocalDate(pageable, noticeCreatedDate, isEnabled)
                .map(NoticeDto::of);
    }
}
