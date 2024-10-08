package com.example.crudpractice.app.repository.querydsl;

import com.example.crudpractice.app.domain.Notice;
import com.example.crudpractice.app.domain.QNotice;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RequiredArgsConstructor
public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<Notice> findAllNoticePagePriorityTopNotice(Pageable pageable, boolean isEnabled) {
        List<Notice> pages = jpaQueryFactory.selectFrom(QNotice.notice)
                .where(
                        QNotice.notice.isEnabled.eq(isEnabled)
                )
                .orderBy(QNotice.notice.isTop.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        return new PageImpl<>(pages, pageable, findAllCounter(isEnabled));
    }

    public Long findAllCounter(boolean isEnabled) {
        return jpaQueryFactory.select(QNotice.notice.count())
                .from(QNotice.notice)
                .where(QNotice.notice.isEnabled.eq(isEnabled))
                .fetchOne();
    }
}
