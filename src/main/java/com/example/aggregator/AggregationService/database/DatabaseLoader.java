package com.example.aggregator.AggregationService.database;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.core.enums.ArticleSource;
import com.example.aggregator.AggregationService.service.ArticleMapperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.ignoreCase;

@Configuration
public class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Autowired
    private ArticleMapperService articleMapperService;



    @Bean
    CommandLineRunner initDatabase(ArticleRepository repository) {







      /* List<Article> articles=new ArrayList<>();

            for(ArticleSource articleSource : ArticleSource.values())
                    articles.addAll(articleMapperService.getArticles(articleSource));



           articles= articles.stream().filter(article->!repository.existsBySourceAndTitle(article.getSource(),article.getTitle())).collect(Collectors.toList());
           repository.saveAll(articles);*/





        return args ->{
            log.info("init db activated");
        };
    }
}

