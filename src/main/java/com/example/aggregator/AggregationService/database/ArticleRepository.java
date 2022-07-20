package com.example.aggregator.AggregationService.database;

import com.example.aggregator.AggregationService.core.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Transactional
    List<Article> findTop30ByOrderByPubTime();
    @Transactional
    Slice<Article> findByCategoryOrderByPubTimeDesc(String category, Pageable pagedArticles);
    boolean existsBySourceAndTitle(String source, String title);
}
