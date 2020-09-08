package com.zlgspace.news.entity;


import com.zlgspace.news.msgparser.MsgDescription;

import java.util.ArrayList;
import java.util.List;


public class NewsEvent {

    private MsgDescription newsEvent ;

    private List<NewsEntity> newsList = new ArrayList<>();

    public NewsEvent(MsgDescription event, List<NewsEntity> newsList){
        setNewsEvent(event);
        setNewsList(newsList);
    }

    public MsgDescription getNewsEvent() {
        return newsEvent;
    }

    public void setNewsEvent(MsgDescription newsEvent) {
        this.newsEvent = newsEvent;
    }

    public List<NewsEntity> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsEntity> newsList) {
        this.newsList = newsList;
    }
}
