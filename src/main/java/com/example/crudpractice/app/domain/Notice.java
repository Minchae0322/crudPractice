package com.example.crudpractice.app.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
public class Notice extends BaseEntity {

    @Comment("공지사항 아이디")
    @Column(name = "notice_id",nullable = false)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("공지사항 제목")
    @Column(name = "title",nullable = false, length = 120)
    private String title;

    @Comment("공지사항 내용")
    @Column(name = "content",nullable = false, columnDefinition = "text")
    private String content;

    @Comment("조회수")
    @Column(name = "hit", nullable = false, columnDefinition = "long default 0")
    private Long hit;

    @Comment("상단 고정 여부")
    @Column(name = "is_top", nullable = false)
    private boolean isTop;

    @Comment("사용 여부")
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @Comment("삭제 여부")
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;


    @Comment("등록일시")
    @Column(name = "created_at", nullable = false)
    private LocalDate created_at;
}
