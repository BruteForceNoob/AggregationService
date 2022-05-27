package com.example.aggregator.AggregationService.config;

import com.example.aggregator.AggregationService.service.ArticleMapperService;
import com.example.aggregator.AggregationService.service.impl.ArticleMapperServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public ArticleMapperService articleMapperService(){
        return new ArticleMapperServiceImpl();
    }

    @Bean
    public RestTemplate getRestTemplate() {return  new RestTemplate();}
}
