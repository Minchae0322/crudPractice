package com.example.crudpractice.app.service;

import com.example.crudpractice.app.core.exception.ResponseException;
import com.example.crudpractice.app.domain.Notice;
import com.example.crudpractice.app.domain.NoticeHit;
import com.example.crudpractice.app.domain.User;
import com.example.crudpractice.app.repository.NoticeHitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class NoticeHitService {

    private final NoticeHitRepository noticeHitRepository;

    /**
     * 게시글 조회수 등록
     *
     * 게시글 조회일 경우 조회수 같이 등록
     *
     * @param notice  - 조회된 게시판
     * @param user - 조회 사용자
     */
    @Transactional
    public void createNoticeHits(Notice notice, User user) {
       noticeHitRepository.save(
                NoticeHit.builder()
                        .notice(notice)
                        .user(user)
                        .build()
        );
    }
}
