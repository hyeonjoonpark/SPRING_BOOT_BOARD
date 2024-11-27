package com.hj.projectboard.domain.article.repository.querydsl;

import com.hj.projectboard.domain.article.Article;
import com.hj.projectboard.domain.article.QArticle;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CustomArticleRepositoryImpl extends QuerydslRepositorySupport implements CustomArticleRepository {
    public CustomArticleRepositoryImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {
        QArticle article = QArticle.article;

//        JPQLQuery<Boolean> query = from(article)
//                .distinct().select(article.hashtag)
//                .where(article.hashtag);
        return List.of();
    }
}
