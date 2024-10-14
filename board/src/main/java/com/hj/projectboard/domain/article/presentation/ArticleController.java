package com.hj.projectboard.domain.article.presentation;

import com.hj.projectboard.domain.article.constant.SearchType;
import com.hj.projectboard.domain.article.presentation.dto.response.ArticleResponse;
import com.hj.projectboard.domain.article.service.ArticleService;
import com.hj.projectboard.domain.article.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/articles")
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public String articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(
                    size = 10,
                    sort = "createdAt",
                    direction = Sort.Direction.DESC
            ) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleResponse> articles = articleService.searchArticles(searchType, searchValue, pageable).map(ArticleResponse::from);
        map.addAttribute("articles", articles);
        List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(),articles.getTotalPages());
        map.addAttribute("paginationBarNumbers", barNumbers);
        map.addAttribute("searchTypes", SearchType.values());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article", "article");
        map.addAttribute("articleComment", List.of());

        return "articles/detail";
    }
}