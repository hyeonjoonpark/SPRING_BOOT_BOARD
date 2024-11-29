package com.hj.projectboard.domain.article.repository;

import com.hj.projectboard.domain.article.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RepositoryRestResource
public interface HashtagRepository extends
        JpaRepository<Hashtag, Long>,
        QuerydslPredicateExecutor<Hashtag> {
    Optional<com.hj.projectboard.domain.article.Hashtag> findByHashtagName(String hashtagName);
    List<com.hj.projectboard.domain.article.Hashtag> findByHashtagNameIn(Set<String> hashtagNames);

    List<String> findAllHashtagNames();
}