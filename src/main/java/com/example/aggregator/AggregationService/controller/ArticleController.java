package com.example.aggregator.AggregationService.controller;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.database.ArticleRepository;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/articles")
    List<Article> all(){
        return articleRepository.findAll(Sort.by(Sort.Direction.ASC,"pubTime"));
    }


}
