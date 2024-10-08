package com.example.crudpractice.app.domain;

import com.example.crudpractice.app.core.util.Utils;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.ObjectUtils;


import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Audited // 데이터 변경이력 테이블 생성을 위해 사용하는 것으로 판단하였습니다.
public class BaseEntity {
    @Comment("생성일시")
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Comment("수정일시")
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Comment("사용 여부")
    @Column(name = "is_enabled", nullable = false)
    protected Boolean isEnabled;

    @Comment("삭제 여부")
    @Column(name = "is_deleted", nullable = false)
    protected Boolean isDeleted;

    @PrePersist
    public void onPrePersist() {
        this.updatedAt = this.createdAt;
        this.isEnabled = ObjectUtils.isEmpty(isEnabled) || isEnabled;
        this.isDeleted = false;
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updatedAt = Utils.now();
    }

}