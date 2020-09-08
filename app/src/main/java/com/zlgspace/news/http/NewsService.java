package com.zlgspace.news.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface NewsService {
    @GET( "reconstruct/article/list/BA10TA81wangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getEntertainmentNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BA8D4A3Rwangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getScientificNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BBM54PGAwangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getImportantNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BA8E6OEOwangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getSportsNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BA8EE5GMwangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getBusinessNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BAI67OGGwangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getMilitaryNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BAI6I0O5wangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getPhoneNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BAI6JOD9wangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getNumericalCodeNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "reconstruct/article/list/BAI6RHDKwangning/{beginNum}-{toNum}.html")
    Call<ResponseBody> getGameNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);

    @GET( "nc/api/video/recommend/Video_Recom/{beginNum}-{toNum}.do?callback=videoList")
    Call<ResponseBody> getVideoNews(@Path("beginNum") int beginNum, @Path("toNum") int toNum);
}
