package com.example.crudpractice.app.domain.dto;

import com.example.crudpractice.app.domain.Notice;
import lombok.*;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeDto {

    private Long noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Long hit;

    private boolean isTop;

    private boolean isEnabled;

    private boolean isDeleted;

    private LocalDateTime created_at;


    public static NoticeDto of(Notice notice){
        return NoticeDto.builder()
                .noticeId(ObjectUtils.isEmpty(notice.getNoticeId())
                        ? null
                        : notice.getNoticeId()
                )
                .noticeTitle(ObjectUtils.isEmpty(notice.getTitle())
                        ? null// enum 으로 변경
                        : notice.getTitle())
                .noticeContent(ObjectUtils.isEmpty(notice.getContent())
                        ? null// enum 으로 변경
                        : notice.getContent())
                .hit(ObjectUtils.isEmpty(notice.getHit())
                        ? null// enum 으로 변경
                        : notice.getHit())
                .isTop(notice.isTop())
                .isEnabled(notice.isEnabled())
                .isDeleted(notice.isDeleted())
                .created_at(ObjectUtils.isEmpty(notice.getCreatedAt())
                        ? null// enum 으로 변경
                        : notice.getCreatedAt())
                .build();
    }

}
