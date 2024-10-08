package com.example.crudpractice.app.repository.querydsl;

import com.example.crudpractice.app.domain.Notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> findAllNoticePagePriorityTopNotice(Pageable pageable, boolean isEnabled);
}
