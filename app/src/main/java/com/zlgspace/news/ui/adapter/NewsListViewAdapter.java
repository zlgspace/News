package com.zlgspace.news.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zlgspace.news.R;
import com.zlgspace.news.entity.NewsEntity;

import java.util.List;


public class NewsListViewAdapter extends BaseAdapter {

    private List<NewsEntity> newsList;

    private LayoutInflater mInflater;

    public NewsListViewAdapter(LayoutInflater inflater, List<NewsEntity> nl){
        newsList = nl;
        mInflater = inflater;
    }

    public void setData(List<NewsEntity> nl){
        newsList = nl;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsEntity item = (NewsEntity)getItem(position);
        if(item.getPriority() >= 200) {
            convertView = getPriority250View(item);
        }else if(item.getHasImg()>=3){
            convertView = getPriority159View(item);
        }else{
            convertView = getPriority159View(item);
        }
        return convertView;
    }


    private View getPriority250View(NewsEntity item){
        View convertView = mInflater.inflate(R.layout.item_newslist250, null);
        ImageView imgView = convertView.findViewById(R.id.imgView);
        TextView titleView = convertView.findViewById(R.id.textView);
        titleView.setText(item.getTitle());
        String imgSrc = item.getImgsrc();
        Glide.with(imgView.getContext()).load(imgSrc).into(imgView);
        return convertView;
    }

    private View getPriority159View(NewsEntity item){
        View convertView = mInflater.inflate(R.layout.item_newlist159, null);
        ImageView imgView = convertView.findViewById(R.id.imgView);
        TextView titleView = convertView.findViewById(R.id.titleTv);
        TextView sourceView = convertView.findViewById(R.id.sourceTv);
        titleView.setText(item.getTitle());
        sourceView.setText(item.getSource());
        String imgSrc = item.getImgsrc();
        Glide.with(imgView.getContext()).load(imgSrc).into(imgView);
        return convertView;
    }

}
