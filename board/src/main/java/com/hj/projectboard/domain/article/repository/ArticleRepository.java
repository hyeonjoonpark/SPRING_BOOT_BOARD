package com.hj.projectboard.domain.article.repository;

import com.hj.projectboard.domain.article.Article;
import com.hj.projectboard.domain.article.QArticle;
import com.hj.projectboard.domain.article.repository.querydsl.CustomArticleRepository;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        CustomArticleRepository,
        QuerydslPredicateExecutor<Article>, // 기본적으로 엔티티 안에 있는 모든 필드에 대한 기본 검색 기능 추가
        QuerydslBinderCustomizer<QArticle> // 추가로 커스텀 검색기능을 추가하기 위해 작성
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // default 값은 false
        bindings.excludeUnlistedProperties(true); // 리스팅을 하지 않은 프로퍼티는 검색 제외
        bindings.including(root.title, root.content, root.hashtags, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%${value}%' 쿼리 생성
        bindings.bind(root.hashtags.any().hashtagName).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Article> findByHashtags(List<String> hashtagName, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(long articleId, String userId);
}
