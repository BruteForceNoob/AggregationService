package com.example.aggregator.AggregationService.core;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Article {

    private @Id @GeneratedValue Long id;
    private String title;



    @Lob
    private String description;
    private String author;
    private String link;
    private String source;
    private String guid;
    private String feedSourceImgLink;



    private LocalDateTime pubTime;



    @Lob
    private String imageURL;

    //private List<String> categories = new ArrayList<>();

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

   /* public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }*/



    public Long getId() {
        return id;
    }

    public Article(){}

    public Article(String title, String description, String author, String link, String source) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.link = link;
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return  getTitle().equals(article.getTitle()) && getSource().equals(article.getSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getSource());
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", source='" + source + '\'' +
                ", guid='" + guid + '\'' +
                ", imageURL='" + imageURL + '\'' +

                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getFeedSourceImgLink() {
        return feedSourceImgLink;
    }

    public void setFeedSourceImgLink(String feedSourceImgLink) {
        this.feedSourceImgLink = feedSourceImgLink;
    }

    public LocalDateTime getPubTime() {
        return pubTime;
    }

    public void setPubTime(LocalDateTime pubTime) {
        this.pubTime = pubTime;
    }
}
