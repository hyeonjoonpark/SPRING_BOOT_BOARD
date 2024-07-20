package com.hj.projectboard.domain.article.presentation.dto;

import com.hj.projectboard.UserAccount;
import com.hj.projectboard.domain.article.Article;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        Set<HashtagDto> hashtagDtos,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) {

    public static ArticleDto of(UserAccountDto userAccountDto, String title, String content, Set<HashtagDto> hashtagDtos) {
        return new ArticleDto(null, userAccountDto, title, content, hashtagDtos, null, null, null, null);
    }

    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, Set<HashtagDto> hashtagDtos, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleDto(id, userAccountDto, title, content, hashtagDtos, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getId(),
                UserAccountDto.from(article.getUserAccount()),
                article.getTitle(),
                article.getContent(),
                article.getHashtags().stream()
                        .map(HashtagDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
                article.getCreatedAt(),
                article.getCreatedBy(),
                article.getModifiedAt(),
                article.getModifiedBy()
        );
    }

    public Article toarticle(UserAccount userAccount) {
        return Article.of(
                userAccount,
                title,
                content
        );
    }

}