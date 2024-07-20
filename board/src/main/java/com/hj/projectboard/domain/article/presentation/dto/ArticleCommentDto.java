package com.hj.projectboard.domain.article.presentation.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.hj.projectboard.domain.article_comment.ArticleComment}
 */
public record ArticleCommentDto(
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy,
        Long id,
        String content
) implements Serializable {

}