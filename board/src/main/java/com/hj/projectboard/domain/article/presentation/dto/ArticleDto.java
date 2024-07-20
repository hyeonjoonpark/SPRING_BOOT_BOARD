package com.hj.projectboard.domain.article.presentation.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.hj.projectboard.domain.article.Article}
 */
public record ArticleDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Long id,
        String title,
        String content,
        String hashTag
) implements Serializable {

}