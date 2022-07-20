package com.example.aggregator.AggregationService.service.impl;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.core.enums.ArticleSource;
import com.example.aggregator.AggregationService.service.ArticleMapperService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ArticleMapperServiceImpl implements ArticleMapperService {

    private static final Logger log = LoggerFactory.getLogger(ArticleMapperService.class);

    public List<Article> getArticles(ArticleSource articleSource){
        List<Article> articles= new ArrayList<>();


        try{

            Document feed = Jsoup.connect(articleSource.sourceUrl).parser(Parser.xmlParser()).get();




            Elements items= feed.getElementsByTag("item");
            if(items==null)
                return articles;


            articles= items.stream().map(item -> getArticleFromItem(item,
                    articleSource.sourceName,
                    articleSource.feedSourceImgUrl, articleSource.category)).collect(Collectors.toList());


        }
        catch (Exception e)
        {
            log.error(e.getMessage());


        }
        return  articles;
    }

    private Article getArticleFromItem(Element item, String feedTitle, String feedImage, String category){
        Article article= new Article();
        try {
            Element itemTitle = item.selectFirst("title");
            Element itemAuthor = item.selectFirst("dc|creator");
            Element itemDesc = item.selectFirst("description");
            Element itemLink = item.selectFirst("link");
            Element itemPubDate = item.selectFirst("pubDate");
            String itemImage = getItemImageURL(item);


            if (feedTitle != null)
                article.setSource(feedTitle);
            if (feedImage != null)
                article.setFeedSourceImgLink(feedImage);
            if (itemTitle != null)
                article.setTitle(itemTitle.text());
            if (itemAuthor != null)
                article.setAuthor(itemAuthor.text());
            else
                article.setAuthor(feedTitle);
            if (itemLink != null)
                article.setLink(itemLink.text());
            if (itemDesc != null) {
                String nodeText = Jsoup.parse(itemDesc.text()).text();
                article.setDescription(nodeText);
            }
            if (itemImage != null)
                article.setImageURL(itemImage);
            else
                article.setImageURL(feedImage);
            if (itemPubDate != null) {
                try {
                    LocalDateTime itemPubTime = LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(itemPubDate.text()));
                    article.setPubTime(itemPubTime);
                } catch (Exception e) {
                    article.setPubTime(LocalDateTime.now());
                }

            } else {
                article.setPubTime(LocalDateTime.now());
            }

            if (category != null && !category.isEmpty())
                article.setCategory(category);
        }
        catch (Exception e){
            log.error(e.getMessage());
        }

        return article;

    }

    private String getItemImageURL(Element item){
        String imageUrl=null;

        try {
            if (item.selectFirst("media|thumbnail") != null) {
                imageUrl = item.selectFirst("media|thumbnail").attr("url");
            } else if (item.selectFirst("media|content") != null) {
                imageUrl = item.selectFirst("media|content").attr("url");
            } else if (item.selectFirst("enclosure") != null) {
                imageUrl = item.selectFirst("enclosure").attr("url");
            } else if (item.selectFirst("content|encoded") != null) {
                Element contentElement = item.selectFirst("content|encoded");
                Document doc = Jsoup.parse(contentElement.text());
                Element imageElement = doc.selectFirst("img");
                if (imageElement != null)
                    imageUrl = imageElement.attr("src");

            } else if (item.selectFirst("description") != null) {
                Element descriptionElement = item.selectFirst("description");
                Document doc = Jsoup.parse(descriptionElement.text());
                Element imageElement = doc.selectFirst("img");
                if (imageElement != null)
                    imageUrl = imageElement.attr("src");

            }
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
        }



        return imageUrl;

    }


}
