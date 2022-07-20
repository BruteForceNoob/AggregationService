package com.example.aggregator.AggregationService.controller;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.core.enums.ArticleSource;
import com.example.aggregator.AggregationService.database.ArticleRepository;
import com.example.aggregator.AggregationService.service.ArticleMapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/loader")
public class ArticleLoader {
    private final ArticleRepository articleRepository;

    private static final Logger log = LoggerFactory.getLogger(ArticleLoader.class);

    @Autowired
    private ArticleMapperService articleMapperService;

    public ArticleLoader(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping
    void loadNewArticles() {
        try{
            List<Article> articles = new ArrayList<>();

            for (ArticleSource articleSource : ArticleSource.values())
                articles.addAll(articleMapperService.getArticles(articleSource));


            articles = articles.stream().filter(article -> !articleRepository.existsBySourceAndTitle(article.getSource(), article.getTitle())).collect(Collectors.toList());
             articleRepository.saveAll(articles);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }

    }
}
