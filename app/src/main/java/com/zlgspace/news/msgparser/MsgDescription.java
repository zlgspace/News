package com.zlgspace.news.msgparser;

import com.zlgspace.msgpraser.annotation.BindEntity;
import com.zlgspace.msgpraser.annotation.MessageDescription;
import com.zlgspace.news.entity.NewsEvent;
import com.zlgspace.news.entity.NewsListEntity;

@MessageDescription
public enum MsgDescription {
    @BindEntity(NewsListEntity.class) onEventEntertainmentNews,
    @BindEntity(NewsListEntity.class) onEventSportsNews,
    @BindEntity(NewsListEntity.class) onEventBusinessNews,
    @BindEntity(NewsListEntity.class) onEventMilitaryNews,
    @BindEntity(NewsListEntity.class) onEventScientificNews,
    @BindEntity(NewsListEntity.class) onEventImportantNews,
    @BindEntity(NewsListEntity.class) onEventPhoneNews,
    @BindEntity(NewsListEntity.class) onEventGameNews,
    @BindEntity(NewsListEntity.class) onEventNumericalCodeNews,
    @BindEntity(NewsEvent.class) onRecNews   //目前这个开发下来，还是统一消息方便点
}
