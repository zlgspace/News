package com.zlgspace.news.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import com.zlgspace.news.constant.HttpContent;
import com.zlgspace.news.entity.WeiBoToSearchEntity;
import com.zlgspace.news.ui.adapter.TopSearchListViewAdapter;
import com.zlgspace.news.utils.ActivityUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * 网页爬取热搜条目
 */
public class WeiBoTopSearchFragment extends BaseListFragment {

    private List<WeiBoToSearchEntity> topSearchEntitys;

    @Override
    public void onCreateView(ViewGroup container, Bundle savedInstanceState) {
        setAdapter(new TopSearchListViewAdapter(getActivity().getLayoutInflater()));
        topSearchEntitys = ((TopSearchListViewAdapter)getAdapter()).getTopSearchEntitys();
        mListView.setOnItemClickListener((parent, view, position, id) -> {
            WeiBoToSearchEntity item = topSearchEntitys.get(position);
            String url = HttpContent.WEB_BASE_URL+item.getUrl();
            String title = item.getTitle();
            ActivityUtils.toNewsDetailActivity(getContext(), NewsDetailActivity.class, url, title);
        });
    }

    @Override
    public void onRefresh() {
        loadWeiBoTopSearch();
    }

    private void onRecData(){
        getActivity().runOnUiThread(() -> {
            getAdapter().notifyDataSetChanged();
            completeRefresh();
        });
    }

    private void loadWeiBoTopSearch(){
        new Thread(){
            @Override
            public void run() {
                Document document = loadHtmlPage(HttpContent.WEB_TOP_SEARCH);

//                Log.d("WeiBoTopSearchFragment",document.toString());

                String htmlStr = document.toString();
                int startIndex = htmlStr.indexOf("<ul class=\"list_a\"> ");
                int endIndex = htmlStr.indexOf("</ul>",startIndex);
//                int startIndex = htmlStr.indexOf("<tbody>");
//                int endIndex = htmlStr.indexOf("</tbody>");

                if(endIndex<0||startIndex<0)
                    return;
                String tbody = htmlStr.substring(startIndex,endIndex);

                saveHtml(tbody);

                String startItemTag = "<li>";
                String endItemTag = "</li>";

                String hrefStartTag = "<a href=";
//                String hrefEndTag = "target=\"_blank\">";
                String hrefEndTag = "\">";
                String titleStartTag = "<span>";
                String titleEndTag = "</span>";

                String emStartTag = "<em>";
                String emEndTag = "</em>";

                String imgStartTag = "<img src=\"";
                String imgEndTag = "\" title=";

                int hrefStartTagLength = hrefStartTag.length();
                int hrefEndTagLength = hrefEndTag.length();

                int titleStartTagLength = titleStartTag.length();
                int titleEndTagLength = hrefEndTag.length();

                int emStartTagLength = emStartTag.length();
                int emEndTagLength = emEndTag.length();

                int imgStartTagLength = imgStartTag.length();
                int imgEndTagLength = imgEndTag.length();

                int start = 0;

                topSearchEntitys.clear();
                int index = 1;
                while((tbody.indexOf(startItemTag, start))!=-1){
                    int itemStart = tbody.indexOf(startItemTag,start);
                    int itemEnd = tbody.indexOf(endItemTag,start);
                    start = itemEnd+endItemTag.length();
                    String item = tbody.substring(itemStart,itemEnd);

                    int end = item.indexOf(hrefEndTag);
                    String href = item.substring(hrefStartTagLength+1, end);
                    String title = "";
                    String em = "0";
                    String img = "";

                    if(href.endsWith("Refer=new_time")) {
                        int titleStart = item.indexOf(titleStartTag);
                        int titleEnd = item.indexOf(titleEndTag);
                        title = item.substring(titleStart + titleStartTagLength, titleEnd);
                    }else{
                        int titleStart = item.indexOf(titleStartTag);
                        int titleEnd = item.indexOf(emStartTag);
                        title = item.substring(titleStart + titleStartTagLength, titleEnd);
                        int emEnd = item.indexOf(emEndTag);
                        em = item.substring(titleEnd + emStartTagLength, emEnd);

                        int imgStart = item.indexOf(imgStartTag);
                        int imgEnd = item.indexOf(imgEndTag);
                        if(imgStart!=-1&&imgEnd!=-1) {
                            img = item.substring(imgStart + imgStartTagLength, imgEnd);
                        }
                    }

                    WeiBoToSearchEntity entity = new WeiBoToSearchEntity(title,href);
                    entity.setEm(em);
                    entity.setImg(img);
                    topSearchEntitys.add(entity);
                }

                onRecData();
            }
        }.start();
    }

    private static Document loadHtmlPage(String url){
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
            System.out.println("url或网络连接错误");
            return null;
        }
    }

    private void saveHtml(String html){
        try {
            File file = new File("/mnt/sdcard/htmlContent");
            if (file.exists())
                file.delete();
            OutputStream out = new FileOutputStream(file);
            out.write(html.getBytes());
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
