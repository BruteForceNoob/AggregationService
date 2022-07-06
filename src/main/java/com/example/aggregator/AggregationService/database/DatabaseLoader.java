package com.example.aggregator.AggregationService.database;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.service.ArticleMapperService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DatabaseLoader {

    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Autowired
    private ArticleMapperService articleMapperService;

    private Object HttpMethod;

    @Bean
    CommandLineRunner initDatabase(ArticleRepository repository) {

        List<String> urls = new ArrayList<>();

        urls.add("https://techcrunch.com/feed/");
        urls.add("https://www.coindesk.com/arc/outboundfeeds/rss/?outputType=xml");
        urls.add("https://arstechnica.com/feed/");



        List<Article> articles=new ArrayList<>();

        for(String url : urls){
            articles.addAll(articleMapperService.getArticles(url));
        }
        Collections.shuffle(articles);
        repository.saveAll(articles);




        return args -> {


            log.info("Preloading " + repository.findAll());
        };
    }
}

