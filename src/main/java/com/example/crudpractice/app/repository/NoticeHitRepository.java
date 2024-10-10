package com.example.crudpractice.app.repository;

import com.example.crudpractice.app.domain.NoticeHit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeHitRepository extends JpaRepository<NoticeHit, Long> {
}
