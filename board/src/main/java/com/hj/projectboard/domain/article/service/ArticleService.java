package com.hj.projectboard.domain.article.service;

import com.hj.projectboard.domain.article.constant.SearchType;
import com.hj.projectboard.domain.article.presentation.dto.ArticleWithCommentDto;
import com.hj.projectboard.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleWithCommentDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentDto getArticle(Long articeId) {
        return null;
    }
}
