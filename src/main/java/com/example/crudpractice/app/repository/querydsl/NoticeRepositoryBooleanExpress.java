package com.example.crudpractice.app.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.example.crudpractice.app.domain.QNotice.notice;

public class NoticeRepositoryBooleanExpress {
    protected BooleanExpression equalsEnabled(Boolean isEnabled) {
        if (isEnabled == null) {
            return null;
        }
        return notice.isEnabled.eq(Boolean.valueOf(isEnabled ? "Y" : "N"));
    }
}
