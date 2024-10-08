package com.example.crudpractice.app.service;


import com.example.crudpractice.app.core.exception.ResponseException;
import com.example.crudpractice.app.core.util.Utils;
import com.example.crudpractice.app.domain.Notice;
import com.example.crudpractice.app.domain.dto.NoticeCreateDto;
import com.example.crudpractice.app.domain.dto.NoticeDto;
import com.example.crudpractice.app.domain.dto.NoticeUpdateDto;
import com.example.crudpractice.app.repository.NoticeRepository;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    /**
     * 기본 편성표 등록
     * @param createDto - 등록 dto
     *
     * @return 생성된 공지사항 아이디
     */

    @Transactional
    public Long create(
            NoticeCreateDto createDto
    ) {

        //notice 생성을 할 때 한번 더 값의 유효성을 판단하는지
        Notice notice = Notice.builder()
                .title(createDto.getNoticeTitle())
                .content(createDto.getNoticeContent())
                .build();

        return noticeRepository.save(notice).getNoticeId();
    }

    public NoticeDto getBasicNotice(Long noticeId) {
        return NoticeDto.of(getNotice(noticeId));
    }

    private Notice getNotice(Long noticeId) {
        return noticeRepository.findByNoticeIdAndDeleted(noticeId, false)
                .orElseThrow(() -> new ResponseException("NOTICE NOT FOUND", HttpStatus.NOT_FOUND));
    }


    public Page<NoticeDto> getNoticePagePriorityTopNotice(Pageable pageable) {
        return noticeRepository.findAllNoticePagePriorityTopNotice(pageable, true)
                .map(NoticeDto::of);
    }

    @Transactional
    public NoticeDto updateNotice(Long noticeId, NoticeUpdateDto noticeUpdateDto) {
        Notice notice = getNotice(noticeId);
        notice.update(noticeUpdateDto);

        return NoticeDto.of(notice);
    }


    @Transactional
    public void delete(Long noticeId) {
        Notice notice = getNotice(noticeId);
        notice.delete();
    }

    public NoticeDto findNotice(Long noticeId) {
        Notice notice = getNotice(noticeId);
        return NoticeDto.of(notice);
    }
}
