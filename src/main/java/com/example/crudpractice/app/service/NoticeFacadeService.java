package com.example.crudpractice.app.service;

import com.example.crudpractice.app.domain.dto.NoticeCreateDto;
import com.example.crudpractice.app.domain.dto.NoticeDto;
import com.example.crudpractice.app.domain.dto.NoticeUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NoticeFacadeService {

    private final NoticeService noticeService;

    /**
     * 공지사항 등록
     * @param createDto - 등록 dto
     * @return 생성된 공지사항 아이디
     */
    public Long create(NoticeCreateDto createDto) {
        return noticeService.create(createDto);
    }

    /**
     * 공지사항 상세 정보 조회
     * @param noticeId - 등록 id
     * @return 공지사항 dto
     */
    public NoticeDto findNotice(Long noticeId) {
        return noticeService.findNotice(noticeId);
    }

    /**
     * 공지사항 상단 고정부터 페이지 조회
     * @param pageable - 해당 페이지 offset 및 size
     * @return 공지사항 페이지
     */
    public Page<NoticeDto> getNoticePagePriorityTopNotice(Pageable pageable) {
        return noticeService.getNoticePagePriorityTopNotice(pageable);
    }

    /**
     * 공지사항 변경
     * @param noticeId - 공지 사항 id
     * @param noticeUpdateDto 공지 사항 변경 dto
     * @return 변경된 공지사항 dto
     */
    public NoticeDto update(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        return noticeService.updateNotice(noticeId, noticeUpdateDto);
    }

    /**
     * 공지사항 삭제
     * @param noticeId - 공지 사항 id
     */
    public void deleteNotice(Long noticeId) {
        noticeService.delete(noticeId);
    }


}
