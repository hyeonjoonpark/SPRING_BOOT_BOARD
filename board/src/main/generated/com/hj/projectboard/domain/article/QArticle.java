package com.hj.projectboard.domain.article;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = -1536398218L;

    public static final QArticle article = new QArticle("article");

    public final com.hj.projectboard.domain.QAuditingFields _super = new com.hj.projectboard.domain.QAuditingFields(this);

    public final SetPath<com.hj.projectboard.domain.article_comment.ArticleComment, com.hj.projectboard.domain.article_comment.QArticleComment> articleComments = this.<com.hj.projectboard.domain.article_comment.ArticleComment, com.hj.projectboard.domain.article_comment.QArticleComment>createSet("articleComments", com.hj.projectboard.domain.article_comment.ArticleComment.class, com.hj.projectboard.domain.article_comment.QArticleComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath hashTag = createString("hashTag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    //inherited
    public final StringPath modifiedBy = _super.modifiedBy;

    public final StringPath title = createString("title");

    public QArticle(String variable) {
        super(Article.class, forVariable(variable));
    }

    public QArticle(Path<? extends Article> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticle(PathMetadata metadata) {
        super(Article.class, metadata);
    }

}

