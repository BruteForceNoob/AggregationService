package com.example.aggregator.AggregationService.service.impl;

import com.example.aggregator.AggregationService.core.Article;
import com.example.aggregator.AggregationService.service.ArticleMapperService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ArticleMapperServiceImpl implements ArticleMapperService {

    public List<Article> getArticles(String url){
        List<Article> articles= new ArrayList<>();


        try{

            Document feed = Jsoup.connect(url).parser(Parser.xmlParser()).get();
            Element feedTitle= feed.selectFirst("channel title");
            Element feedImage= feed.selectFirst("image url");
          /*  if(feedTitle!=null)
                feedSource=feedTitle.text();
            if(feedImage!=null)
                feedImageSource=feedImage.text();*/

            Elements items= feed.getElementsByTag("item");
            if(items==null)
                return articles;

          //  String finalFeedSource = feedSource;
           // String finalFeedImageSource = feedImageSource;
            articles= items.stream().map(item -> getArticleFromItem(item,
                    feedTitle,
                    feedImage)).collect(Collectors.toList());


        }
        catch (Exception e)
        {
            return articles;
        }
        return  articles;
    }

    private Article getArticleFromItem(Element item, Element feedTitle, Element feedImage){
        Element itemTitle= item.selectFirst("title");
        Element itemAuthor= item.selectFirst("dc|creator");
        Element itemDesc= item.selectFirst("description");
        Element itemLink=item.selectFirst("link");
        Element itemPubDate=item.selectFirst("pubDate");
        String itemImage= getItemImageURL(item);
        Article article= new Article();


        if(feedTitle!=null)
            article.setSource(feedTitle.text());
        if(feedImage!=null)
            article.setFeedSourceImgLink(feedImage.text());
        if(itemTitle!=null)
            article.setTitle(itemTitle.text());
        if(itemAuthor!=null)
            article.setAuthor(itemAuthor.text());
        if(itemLink!=null)
            article.setLink(itemLink.text());
        if(itemDesc!=null)
            article.setDescription(itemDesc.text());
        if(itemImage!=null)
            article.setImageURL(itemImage);
        if(itemPubDate!=null){
            LocalDateTime itemPubTime = LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(itemPubDate.text()));
            article.setPubTime(itemPubTime);

        }
        else{
            article.setPubTime(LocalDateTime.now());
        }


        return article;

    }

    private String getItemImageURL(Element item){
        String imageUrl=null;
        Element contentElement= item.selectFirst("content|encoded");
        if(contentElement!=null){
            Document doc=Jsoup.parse(contentElement.text());
            Element imageElement=doc.selectFirst("img");
            if(imageElement!=null)
               imageUrl= imageElement.attr("src");

        }
        else if(item.selectFirst("media|thumbnail")!=null){
            imageUrl=item.selectFirst("media|thumbnail").attr("url");
        }
        else if(item.selectFirst("media|content")!=null){
            imageUrl= item.selectFirst("media|content").attr("url");
        }

        return imageUrl;

    }


}
