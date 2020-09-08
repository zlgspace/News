package com.zlgspace.news.http;

import android.util.Log;

import com.zlgspace.msgpraser.MsgParser;
import com.zlgspace.news.constant.HttpContent;
import com.zlgspace.news.entity.NewsEntity;
import com.zlgspace.news.entity.NewsEvent;
import com.zlgspace.news.entity.NewsListEntity;
import com.zlgspace.news.msgparser.MsgDescription;
import com.zlgspace.news.utils.GsonUtils;
import com.zlgspace.news.utils.StrUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class HttpUtils {

    private static Retrofit mRetrofit;

    private static NewsService mNewsService;

    static{
        initRetrofit();
    }

    private static void initRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpContent.WY_BASE_URL)
                .build();

        mNewsService = mRetrofit.create(NewsService.class);
    }

    public static void loadNews(MsgDescription newsType){
        switch (newsType){
            case onEventBusinessNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventBusinessNews,mNewsService.getBusinessNews(0,20));
                break;
            case onEventEntertainmentNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventEntertainmentNews,mNewsService.getEntertainmentNews(0,20));
                break;
            case onEventGameNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventGameNews,mNewsService.getGameNews(0,20));
                break;
            case onEventImportantNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventImportantNews,mNewsService.getImportantNews(0,20));
                break;
            case onEventMilitaryNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventMilitaryNews,mNewsService.getMilitaryNews(0,20));
                break;
            case onEventNumericalCodeNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventNumericalCodeNews,mNewsService.getNumericalCodeNews(0,20));
                break;
            case onEventPhoneNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventPhoneNews,mNewsService.getPhoneNews(0,20));
                break;
            case onEventScientificNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventScientificNews,mNewsService.getScientificNews(0,20));
                break;
            case onEventSportsNews:
                NewsLoader.simpleLoadNews(MsgDescription.onEventSportsNews,mNewsService.getSportsNews(0,20));
                break;
        }
    }

    private static String getRstJsonStr(String rst){
        if(StrUtils.isEmptyOrNull(rst)){
            return "";
        }
        String r = rst.replace("artiList(","");
        return r.substring(0,r.length()-1);
    }

    private static class NewsLoader{

        private MsgDescription eventId;

        private Call<ResponseBody> httpCall;

        public NewsLoader(MsgDescription evId,Call<ResponseBody> call){
            eventId = evId;
            httpCall = call;
        }

        public void load(){
            if(httpCall==null || eventId==null)
                return;

            httpCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d("HttpUtils", eventId+":isSuccessful = "+response.isSuccessful());
                    if(!response.isSuccessful()) {
                        return;
                    }
                    try {
                        String rst = new String(response.body().bytes());
                        Log.d("HttpUtils", eventId+":" + rst);
                        rst = getRstJsonStr(rst);
                        JSONObject jsonObject = new JSONObject(rst);
                        JSONArray jsonNames = jsonObject.names();
                        JSONArray jsonNewsItemArr = jsonObject.getJSONArray(jsonNames.getString(0));
                        NewsListEntity nle = new NewsListEntity();
                        nle.setNewsList(GsonUtils.jsonToList(jsonNewsItemArr.toString(),NewsEntity[].class));
                        MsgParser.sendMsg(eventId.toString(),nle);
                        MsgParser.sendMsg(MsgDescription.onRecNews.toString(),new NewsEvent(eventId,nle.getNewsList()));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("HttpUtils",eventId+":onFailure",t);
                }
            });
        }

        public static void simpleLoadNews(MsgDescription evId,Call<ResponseBody> call){
            NewsLoader loader = new NewsLoader(evId,call);
            loader.load();
        }
    }

}
