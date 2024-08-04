package com.hj.projectboard.domain.article.presentation.dto.response;

import com.hj.projectboard.domain.article.presentation.dto.ArticleDto;
import com.hj.projectboard.domain.article.presentation.dto.HashtagDto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.hj.projectboard.domain.article.Article}
 */
public record ArticleResponse(
        Long id,
        String title,
        String content,
        Set<String> hashtags,
        LocalDateTime createdAt,
        String email,
        String nickname
) implements Serializable {

  public static ArticleResponse of(Long id, String title, String content, Set<String> hashtags, LocalDateTime createdAt, String email, String nickname) {
    return new ArticleResponse(id, title, content, hashtags, createdAt, email, nickname);
  }

  public static ArticleResponse from(ArticleDto articleDto) {
    String nickname = articleDto.userAccountDto().nickname();
    if (nickname == null || nickname.isBlank()) {
      nickname = articleDto.userAccountDto().userId();
    }

    return new ArticleResponse(
            articleDto.id(),
            articleDto.title(),
            articleDto.content(),
            articleDto.hashtagDtos().stream()
                    .map(HashtagDto::hashtagName)
                    .collect(Collectors.toUnmodifiableSet())
            ,
            articleDto.createdAt(),
            articleDto.userAccountDto().email(),
            nickname
    );
  }

}