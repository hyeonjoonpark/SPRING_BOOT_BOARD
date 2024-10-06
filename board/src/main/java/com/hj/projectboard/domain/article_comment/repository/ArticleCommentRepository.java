package com.hj.projectboard.domain.article_comment.repository;

import com.hj.projectboard.domain.article.QArticle;
import com.hj.projectboard.domain.article_comment.ArticleComment;
import com.hj.projectboard.domain.article_comment.QArticleComment;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Integer>,
        QuerydslPredicateExecutor<ArticleComment>, // 기본적으로 엔티티 안에 있는 모든 필드에 대한 기본 검색 기능 추가
        QuerydslBinderCustomizer<QArticleComment> // 추가로 커스텀 검색기능을 추가하기 위해 작성
{
    @Override
    default void customize(QuerydslBindings bindings, QArticleComment root) {
        // default 값은 false
        bindings.excludeUnlistedProperties(true); // 리스팅을 하지 않은 프로퍼티는 검색 제외
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

    List<ArticleComment> findByArticle_Id(Long articleId);

    void deleteByIdAndUserAccount_UserId(Long articleCommentId, String userId);
}
