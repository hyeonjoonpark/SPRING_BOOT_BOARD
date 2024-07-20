package com.hj.projectboard.domain.article.presentation.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleCommentDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Long id,
        String content
) implements Serializable {

}