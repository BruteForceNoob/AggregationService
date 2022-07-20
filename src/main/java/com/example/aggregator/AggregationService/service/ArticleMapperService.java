package com.example.aggregator.AggregationService.service;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.core.enums.ArticleSource;

import java.util.List;

public interface ArticleMapperService {
    public List<Article> getArticles(ArticleSource articleSource);
}
