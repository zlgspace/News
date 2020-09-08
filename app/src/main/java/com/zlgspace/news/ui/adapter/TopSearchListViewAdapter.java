package com.zlgspace.news.ui.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zlgspace.news.R;
import com.zlgspace.news.entity.WeiBoToSearchEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zl on 2020/4/29.
 */
public class TopSearchListViewAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private List<WeiBoToSearchEntity> topSearchEntitys = new ArrayList<>();

    public TopSearchListViewAdapter(LayoutInflater inflater){
        this.inflater = inflater;
    }

    public List<WeiBoToSearchEntity> getTopSearchEntitys(){
        return topSearchEntitys;
    }


    @Override
    public int getCount() {
        return topSearchEntitys.size();
    }

    @Override
    public Object getItem(int position) {
        return topSearchEntitys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_topsearch,null);
        }

        TextView numberTv = convertView.findViewById(R.id.numberTv);
        TextView titleTv = convertView.findViewById(R.id.titleTv);
        TextView emTv = convertView.findViewById(R.id.emTv);
        ImageView iconIv = convertView.findViewById(R.id.iconIv);

        WeiBoToSearchEntity entity = topSearchEntitys.get(position);

        numberTv.setText(""+(position+1));
        titleTv.setText(entity.getTitle());

        if(!TextUtils.isEmpty(entity.getEm())){
            emTv.setText(entity.getEm());
        }else{
            emTv.setText("");
        }

//        if(!TextUtils.isEmpty(entity.getImg())){
//            iconIv.setVisibility(View.VISIBLE);
//            String imgSrc = HttpContent.WEB_TOP_SEARCH+entity.getImg();
//            Glide.with(iconIv.getContext()).load(imgSrc).into(iconIv);
//        }else{
//            iconIv.setVisibility(View.GONE);
//        }
        return convertView;
    }
}
