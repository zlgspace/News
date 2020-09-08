package com.zlgspace.news.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zlgspace.news.R;
import com.zlgspace.news.baoyz.widget.PullRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;


public abstract class BaseListFragment extends BaseFragment {

    @BindView(R.id.listView)
    protected ListView mListView;

    @BindView(R.id.pullRefreshLayout)
    protected PullRefreshLayout mPullRefreshLayout;

    protected BaseAdapter mNewsListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        ButterKnife.bind(this,view);

        mPullRefreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_MATERIAL);
        mPullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BaseListFragment.this.onRefresh();
            }
        });

        onCreateView((ViewGroup) view,savedInstanceState);
        super.onCreateView(inflater,container,savedInstanceState);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        onRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    protected BaseAdapter getAdapter(){
        return mNewsListViewAdapter;
    }

    protected void setAdapter(BaseAdapter adapter){
        mNewsListViewAdapter = adapter;
        mListView.setAdapter(mNewsListViewAdapter);
    }

    protected void notifyDataSetChanged(){
        mNewsListViewAdapter.notifyDataSetChanged();
    }

    protected void completeRefresh(){
        mPullRefreshLayout.setRefreshing(false);
    }

    public abstract void onCreateView(ViewGroup container, Bundle savedInstanceState);

    public abstract void onRefresh();
}
