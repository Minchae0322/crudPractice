package com.example.crudpractice.app.repository;

import com.example.crudpractice.app.domain.Notice;
import com.example.crudpractice.app.repository.querydsl.NoticeRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {

    Optional<Notice> findByNoticeIdAndDeleted(Long id, boolean isDeleted);
}
