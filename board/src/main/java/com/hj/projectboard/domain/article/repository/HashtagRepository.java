package com.hj.projectboard.domain.article.repository;

import com.hj.projectboard.domain.article.Hashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HashtagRepository extends JpaRepository<Hashtag, Long> {

    List<Hashtag> findByHashtagNameIn(Set<String> hashtagNames);
}
