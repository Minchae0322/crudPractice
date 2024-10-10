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

import static com.example.crudpractice.app.core.util.Utils.MAX_HIT_COUNT;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Audited // 데이터 변경이력 테이블 생성을 위해 사용하는 것으로 판단하였습니다.
public class Notice {

    @Comment("공지사항 아이디")
    @Column(name = "notice_id",nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeId;


    @Column(name = "title", nullable = false, length = 120)
    private String title;

    @Comment("공지사항 내용")
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Comment("조회수")
    @Column(name = "hit", columnDefinition = "bigint default 0")
    private long hit;

    @Comment("상단 고정 여부")
    @Column(name = "is_top", nullable = false)
    private boolean isTop;

    @Comment("사용 여부")
    @Column(name = "is_enabled", nullable = false)
    private boolean isEnabled;

    @Comment("삭제 여부")
    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;


    @Comment("등록일시")
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onPrePersist() {
        this.createdAt = Utils.now();
        this.isEnabled = ObjectUtils.isEmpty(isEnabled) || isEnabled;
        this.deleted = false;
    }

    public void delete() {
        this.deleted = true;
    }

    public void update(NoticeUpdateDto noticeUpdateDto) {
        this.content = ObjectUtils.isEmpty(noticeUpdateDto.getNoticeContent())
                ? this.content
                : noticeUpdateDto.getNoticeContent();
        this.title = ObjectUtils.isEmpty(noticeUpdateDto.getNoticeTitle())
                ? this.title
                : noticeUpdateDto.getNoticeTitle();
    }

    public void updateNoticeTopStatus(Boolean isTop) {
        this.isTop = ObjectUtils.isEmpty(isTop)
                ? this.isTop
                : isTop;
    }

    public void hitCountUp() {
        if(hit > MAX_HIT_COUNT) {
            this.hit = MAX_HIT_COUNT;
            return;
        }
        this.hit++;

    }


}
