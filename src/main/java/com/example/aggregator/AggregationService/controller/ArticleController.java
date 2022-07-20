package com.example.aggregator.AggregationService.controller;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.database.ArticleRepository;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/articles")
@CrossOrigin()
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }



  /*  List<Article> all(){
       // return articleRepository.findAll(Sort.by(Sort.Direction.ASC,"pubTime")).subList(0,30);
        return articleRepository.findAll();
    }
*/
    @GetMapping
    List<Article> getPagedArticles(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "15") Integer size, @RequestParam(defaultValue = "Tech") String category){
        Pageable pagedArticles= PageRequest.of(page, size);
        Slice<Article> articlePage= articleRepository.findByCategoryOrderByPubTimeDesc(category, pagedArticles);
        if(articlePage.hasContent())
            return articlePage.getContent();
        return new ArrayList<>();


    }





}
