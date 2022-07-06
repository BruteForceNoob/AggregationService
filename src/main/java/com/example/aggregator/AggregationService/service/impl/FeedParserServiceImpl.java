package com.example.aggregator.AggregationService.service.impl;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class FeedParserServiceImpl {
    public void parse() throws Exception{
        String url="https://techcrunch.com/feed/";
        Document feed = Jsoup.connect(url).parser(Parser.xmlParser()).get();



       Element feedTitle=  feed.selectFirst("title");
       Element feedImage = feed.selectFirst("image");
       Element feedImageUrl= feedImage.selectFirst("url");
       Elements items= feed.getElementsByTag("item");
       System.out.println("Feed Title: "+ feedTitle.text()+ "\n" +
               "Feed Image URL: "+ feedImageUrl.text());
       int c=0;
       for(Element item : items){
           System.out.println("#############################");
           System.out.println("Article Title: "+ item.selectFirst("title").text());
           System.out.println("Author: "+ item.selectFirst("dc|creator").text());
           System.out.println("Article Link "+ item.selectFirst("link").text());
           System.out.println("Desc: "+ item.selectFirst("description").text());
           c+=1;
           if(c>3)
               break;



       }



    }


}
