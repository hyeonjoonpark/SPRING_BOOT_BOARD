package com.hj.projectboard.domain.article.presentation.dto;

import com.hj.projectboard.domain.user.UserAccount;
import com.hj.projectboard.domain.article.Article;
import com.hj.projectboard.domain.article_comment.ArticleComment;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleCommentDto(
        Long id,
        Long articleId,
        UserAccountDto userAccountDto,
        Long parentCommentId,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
) implements Serializable {
    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, String content) {
        return ArticleCommentDto.of(articleId, userAccountDto, null, content);
    }

    public static ArticleCommentDto of(Long articleId, UserAccountDto userAccountDto, Long parentCommentId, String content) {
        return ArticleCommentDto.of(null, articleId, userAccountDto, parentCommentId, content, null, null, null, null);
    }

    public static ArticleCommentDto of(Long id, Long articleId, UserAccountDto userAccountDto, Long parentCommentId, String content, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleCommentDto(id, articleId, userAccountDto, parentCommentId, content, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleCommentDto from(ArticleComment articleComment) {
        return new ArticleCommentDto(
                articleComment.getId(),
                articleComment.getArticle().getId(),
                UserAccountDto.from(articleComment.getArticle().getUserAccount()),
                articleComment.getParentCommentId(),
                articleComment.getContent(),
                articleComment.getCreatedAt(),
                articleComment.getCreatedBy(),
                articleComment.getModifiedAt(),
                articleComment.getModifiedBy()
        );
    }

    public ArticleComment toarticleComment(Article article, UserAccount userAccount) {
        return ArticleComment.of(
                article,
                userAccount,
                content
        );
    }

    public ArticleComment toEntity(Article article, UserAccount userAccount) {
        return ArticleComment.of(
                article,
                userAccount,
                content
        );
    }
}