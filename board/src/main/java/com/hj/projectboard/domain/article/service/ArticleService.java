package com.hj.projectboard.domain.article.service;

import com.hj.projectboard.domain.article.Article;
import com.hj.projectboard.domain.article.Hashtag;
import com.hj.projectboard.domain.article.constant.SearchType;
import com.hj.projectboard.domain.article.presentation.dto.ArticleDto;
import com.hj.projectboard.domain.article.presentation.dto.ArticleWithCommentDto;
import com.hj.projectboard.domain.article.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final HashtagService hashtagService;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> articleRepository.findByUserAccount_UserIdContaining(searchKeyword, pageable).map(ArticleDto::from);
            case NICKNAME -> articleRepository.findByUserAccount_NicknameContaining(searchKeyword, pageable).map(ArticleDto::from);
            case HASHTAG -> articleRepository.findByHashtag("#" + searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleWithCommentDto getArticle(Long articeId) {
        return articleRepository.findById(articeId)
                .map(ArticleWithCommentDto::from)
                .orElseThrow(() -> new EntityNotFoundException("게시글이 존재하지 않습니다"));
    }

    public void saveArticle(ArticleDto articleDto) {
        articleRepository.save(articleDto.toEntity()); // TODO : add UserAccount
    }

    public void updateArticle(ArticleDto articleDto) {
        try {
            Article article = articleRepository.getReferenceById(articleDto.id());
            if(articleDto.title() != null)  {
                article.setTitle(articleDto.title());
            }

            if(articleDto.content() != null)  {
                article.setContent(articleDto.content());
            }

            Set<Long> hashtagIds = article.getHashtags().stream()
                    .map(Hashtag::getId)
                    .collect(Collectors.toUnmodifiableSet());

            article.clearHashtags();
            articleRepository.flush();

            hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles); // TODO

            Set<Hashtag> hashtags = renewHashtagsFromContent(articleDto.content()); // TODO

            article.setHashtags(hashtags);
            articleRepository.save(article);
        } catch (EntityNotFoundException e) {
            log.warn("게시글 수정을 실패하였습니다");
        }
    }
}
