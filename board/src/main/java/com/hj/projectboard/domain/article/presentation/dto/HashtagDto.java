package com.hj.projectboard.domain.article.presentation.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.hj.projectboard.domain.article.Article}
 */
public record HashtagDto(
        Long id,
        String hashtagName,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {

}