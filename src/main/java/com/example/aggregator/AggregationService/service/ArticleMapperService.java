package com.example.aggregator.AggregationService.service;

import com.example.aggregator.AggregationService.core.Article;
import com.rometools.rome.feed.synd.SyndEntry;

import java.util.List;

public interface ArticleMapperService {
    public List<Article> getArticles(String url);
}
