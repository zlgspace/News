package com.zlgspace.news.entity;

public class WeiBoToSearchEntity {
    private String title;
    private String url;
    private String em;
    private String img;

    public WeiBoToSearchEntity(){

    }

    public WeiBoToSearchEntity(String title, String url){
        setTitle(title);
        setUrl(url);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
