package com.example.aggregator.AggregationService.core.enums;

import org.springframework.format.datetime.DateFormatter;

import java.time.format.DateTimeFormatter;

public enum ArticleSource {

    Kotaku("Kotaku", "https://upload.wikimedia.org/wikipedia/commons/2/28/Kotaku_logo.svg", "https://kotaku.com/rss", "Gaming" ),
    WSJ("WSJ", "https://www.wsj.com/img/wsj_sm_logo.gif", "https://feeds.a.dj.com/rss/WSJcomUSBusiness.xml","Business"),
    GlobalNewsSports("Global News", "https://globalnews.ca/wp-content/uploads/2018/03/new_square-e1647018225390.png", "https://globalnews.ca/sports/feed/", "Sports"),
    Intercept("Intercept", "https://theintercept.com/assets/d4d51f698881e32ac3928e12e5631e93.png", "https://theintercept.com/feed/?lang=en", "News"),
    IGN("IGN", "https://s3.amazonaws.com/o.assets.images.ign.com/kraken/IGN-Logo-RSS.png", "http://feeds.feedburner.com/ign/games-all", "Gaming"),
    TechCrunch("TechCrunch", "https://techcrunch.com/wp-content/uploads/2015/02/cropped-cropped-favicon-gradient.png?w=32", "https://techcrunch.com/feed/", "Tech"),
    ArsTechnica("ArsTechnica", "https://cdn.arstechnica.net/wp-content/uploads/2016/10/cropped-ars-logo-512_480-32x32.png", "https://arstechnica.com/feed/", "Tech"),
    CoinDesk("CoinDesk", "https://www.coindesk.com/resizer/fTK3gATlyciJ-BZG2_OP12niDe0=/144x32/downloads.coindesk.com/arc/failsafe/feeds/coindesk-feed-logo.png", "https://www.coindesk.com/arc/outboundfeeds/rss/?outputType=xml", "Tech" );

    public final String sourceName;
    public final String feedSourceImgUrl;
    public final String sourceUrl;
    public final String category;

    private ArticleSource(String sourceName, String feedSourceImgUrl,  String sourceUrl, String category ) {
        this.sourceName=sourceName;
        this.feedSourceImgUrl=feedSourceImgUrl;
        this.sourceUrl=sourceUrl;
        this.category=category;
    }


}