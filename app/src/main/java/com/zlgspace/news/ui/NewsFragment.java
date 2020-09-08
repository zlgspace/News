package com.zlgspace.news.ui;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.zlgspace.news.entity.NewsEntity;
import com.zlgspace.news.entity.NewsEvent;
import com.zlgspace.news.ui.adapter.NewsListViewAdapter;
import com.zlgspace.news.utils.ActivityUtils;


import java.util.ArrayList;
import java.util.List;


public abstract class NewsFragment extends BaseListFragment {

    protected List<NewsEntity> newsList = new ArrayList<>();

    public void onCreateView(ViewGroup container, Bundle savedInstanceState) {
        NewsListViewAdapter adapter = new NewsListViewAdapter(getActivity().getLayoutInflater(),newsList);
        setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsEntity ne = newsList.get(position);
                String url = ne.getSkipURL() != null ? ne.getSkipURL() : ne.getUrl();
                String title = ne.getTitle();
                ActivityUtils.toNewsDetailActivity(getContext(), NewsDetailActivity.class, url, title);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onRefresh() {
        findNews();
    }

    protected NewsListViewAdapter  getAdapter(){
        return (NewsListViewAdapter)super.getAdapter();
    }

    public abstract void findNews();

    public abstract void onRecNews(NewsEvent newEvent);
}
