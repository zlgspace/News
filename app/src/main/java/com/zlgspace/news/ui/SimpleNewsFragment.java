package com.zlgspace.news.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zlgspace.msgpraser.MsgParser;
import com.zlgspace.msgpraser.annotation.CallbackMethod;
import com.zlgspace.news.entity.NewsEvent;
import com.zlgspace.news.http.HttpUtils;
import com.zlgspace.news.msgparser.MsgDescription;

public class SimpleNewsFragment extends NewsFragment {

    private MsgDescription newsType;

    public SimpleNewsFragment(MsgDescription newsType){
        this.newsType = newsType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("SimpleNewsFragment","onCreateView");
        MsgParser.register(this);//本来想放在BaseFragment onCreateView中调用，但是莫名的会报错，原因未知
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        MsgParser.unRegister(this);
    }

    @Override
    public void findNews() {
        HttpUtils.loadNews(newsType);
    }

    @CallbackMethod
    public void onRecNews(NewsEvent newEvent) {
        Log.d("SimpleNewsFragment", "onRecNews newEvent.getNewsList() = " + newEvent.getNewsList().size()+",newEvent.getNewsEvent() = "+newEvent.getNewsEvent()+",newsType = "+newsType);
        if (newEvent.getNewsEvent() != newsType) {
            return;
        }
        completeRefresh();
        newsList =  newEvent.getNewsList();
        getAdapter().setData(newsList);
        notifyDataSetChanged();
    }

}
