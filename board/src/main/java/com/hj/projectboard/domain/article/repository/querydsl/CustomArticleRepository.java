package com.hj.projectboard.domain.article.repository.querydsl;

import java.util.List;

public interface CustomArticleRepository {
    List<String> findAllDistinctHashtags();
}
