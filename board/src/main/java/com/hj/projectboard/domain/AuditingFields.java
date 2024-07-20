package com.hj.projectboard.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

// 중복 필드 추출

@ToString
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingFields {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) // 날짜 포맷팅
    @CreatedDate
    @Column(nullable = false, updatable = false) // updatable = false : 업데이트 불가 컬럼
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, length = 100)
    protected String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;

    @LastModifiedBy
    @Column(nullable = false, length = 100)
    protected String modifiedBy;
}
