package com.example.crudpractice.app.service;


import com.example.crudpractice.app.core.exception.ResponseException;

import com.example.crudpractice.app.domain.Notice;
import com.example.crudpractice.app.domain.User;
import com.example.crudpractice.app.domain.dto.NoticeCreateDto;
import com.example.crudpractice.app.domain.dto.NoticeDto;
import com.example.crudpractice.app.domain.dto.NoticeUpdateDto;
import com.example.crudpractice.app.repository.NoticeRepository;

import com.example.crudpractice.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;




@Transactional()
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    /**
     * 공지사항 등록
     * @param createDto - 등록 dto
     * @return 생성된 공지사항 아이디
     */
    @Transactional
    public Long create(NoticeCreateDto createDto) {
        //notice 생성을 할 때 한번 더 값의 유효성을 판단하는지
        Notice notice = createDto.toEntity();

        return noticeRepository.save(notice).getNoticeId();
    }

    public Notice getNotice(Long noticeId) {
        return noticeRepository.findByNoticeIdAndDeleted(noticeId, false)
                .orElseThrow(() -> new ResponseException("NOTICE NOT FOUND", HttpStatus.NOT_FOUND));
    }

    /**
     * 공지사항 상세 정보 조회
     * @param noticeId - 등록 id
     * @return 공지사항 dto
     */
    public NoticeDto findNotice(Long noticeId) {
        Notice notice = getNotice(noticeId);
        upHitCount(notice);
        return NoticeDto.of(notice);
    }

    /**
     * 공지사항 상세 정보 조회
     * @param notice - 공지사항 상세 정보
     */
    private void upHitCount(Notice notice) {
        notice.hitCountUp();
    }

    /**
     * 공지사항 상단 고정부터 페이지 조회
     * @param pageable - 해당 페이지 offset 및 size
     * @return 공지사항 페이지
     */
    public Page<NoticeDto> getNoticePagePriorityTopNotice(Pageable pageable) {
        return noticeRepository.findAllNoticePagePriorityTopNotice(pageable, true)
                .map(NoticeDto::of);
    }

    /**
     * 공지사항 변경
     * @param noticeId - 공지 사항 id
     * @param noticeUpdateDto 공지 사항 변경 dto
     * @return 변경된 공지사항 dto
     */
    @Transactional
    public NoticeDto updateNotice(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        Notice notice = getNotice(noticeId);
        notice.update(noticeUpdateDto);

        return NoticeDto.of(notice);
    }

    /**
     * 공지사항 삭제
     * @param noticeId - 공지 사항 id
     */
    @Transactional
    public void delete(Long noticeId) {
        Notice notice = getNotice(noticeId);
        notice.delete();
    }

    /**
     * 상단 고정 상태 변경
     * @param noticeId - 공지 사항 id
     * @param isTop - 공지 사항 고정 여부
     */
    public Long updateNoticeStatus(Long noticeId, Boolean isTop) {
        Notice updateNoticeTop = getNotice(noticeId);
        updateNoticeTop.updateNoticeTopStatus(isTop);

        return updateNoticeTop.getNoticeId();
    }



}
