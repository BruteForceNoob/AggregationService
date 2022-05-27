package com.example.aggregator.AggregationService.database;

import com.example.aggregator.AggregationService.core.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
