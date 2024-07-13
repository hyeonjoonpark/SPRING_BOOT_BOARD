package com.hj.projectboard.domain.article_comment.repository;

import com.hj.projectboard.domain.article_comment.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCommentRepository extends JpaRepository<ArticleComment, Integer> {

}
