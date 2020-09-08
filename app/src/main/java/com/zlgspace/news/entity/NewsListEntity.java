package com.zlgspace.news.entity;

import java.util.ArrayList;
import java.util.List;

public class NewsListEntity {

    private List<NewsEntity> newsList = new ArrayList<>();

    public List<NewsEntity> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<NewsEntity> newsList) {
        this.newsList = newsList;
    }
}
