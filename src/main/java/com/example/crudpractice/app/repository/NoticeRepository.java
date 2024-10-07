package com.example.crudpractice.app.repository;

import com.example.crudpractice.app.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Long, Notice> {
}
