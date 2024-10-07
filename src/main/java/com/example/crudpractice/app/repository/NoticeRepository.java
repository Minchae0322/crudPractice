package com.example.crudpractice.app.repository;

import com.example.crudpractice.app.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    Optional<Notice> findByNoticeIdAndIsDeleted(Long id, boolean isDeleted);
}
