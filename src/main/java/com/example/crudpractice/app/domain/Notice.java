package com.example.crudpractice.app.domain;

import com.example.crudpractice.app.core.util.Utils;
import com.example.crudpractice.app.domain.dto.NoticeUpdateDto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.envers.Audited;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Audited // 데이터 변경이력 테이블 생성을 위해 사용하는 것으로 판단하였습니다.
public class Notice {


    @Column(name = "notice_id",nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;


    @Column(name = "title",nullable = false, length = 120)
    private String title;


    @Column(name = "content", nullable = false, columnDefinition = "text")
    private String content;


    @Column(name = "hit", nullable = false, columnDefinition = "BIGINT default 0")
    private Long hit;


    @Column(name = "is_top", nullable = false)
    private boolean isTop;


    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;


    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;



    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = Utils.now();
        this.isEnabled = ObjectUtils.isEmpty(isEnabled) || isEnabled;
        this.isDeleted = false;
    }

    public void update(NoticeUpdateDto noticeUpdateDto) {
        this.content = ObjectUtils.isEmpty(noticeUpdateDto.getNoticeContent())
                ? this.content
                : noticeUpdateDto.getNoticeContent();
        this.title = ObjectUtils.isEmpty(noticeUpdateDto.getNoticeTitle())
                ? this.title
                : noticeUpdateDto.getNoticeTitle();
        this.isTop = ObjectUtils.isEmpty(noticeUpdateDto.isTop())
                ? this.isTop
                : noticeUpdateDto.isTop();
    }


}
