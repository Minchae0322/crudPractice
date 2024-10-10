package com.example.crudpractice.app.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "notice_hit")
@Entity
@Audited
public class NoticeHit {

    @Comment("공지사항 아이디")
    @Column(name = "notice_hits_id",nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeHitId;

    @Comment("공지사항")
    @ManyToOne(fetch = FetchType.LAZY)
    private Notice notice;

    @Comment("조회한 유저")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;



}
