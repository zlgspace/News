package com.zlgspace.news.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.CookieSyncManager;
import com.tencent.smtt.utils.TbsLog;
import com.zlgspace.news.R;
import com.zlgspace.news.ui.view.X5WebView;


import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsDetailActivity extends Activity {

    @BindView(R.id.webView)
    protected FrameLayout mViewParent;

    @BindView(R.id.backBtn)
    protected Button backBtn;

    @BindView(R.id.titleTv)
    protected TextView titleTv;

    private X5WebView mWebView;

    private String mIntentUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdetail);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        mIntentUrl = intent.getStringExtra("URL");
        String title = intent.getStringExtra("TITLE");
        Log.d("NewsDetailActivity","NewsDetailActivity -> URL = "+mIntentUrl);
        titleTv.setText(title);

        init();
    }

    @OnClick(R.id.backBtn)
    public void backBtnClick(){
        this.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null)
            mWebView.destroy();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private View init() {

        mWebView = new X5WebView(this, null);

        mViewParent.addView(mWebView, new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.FILL_PARENT,
                FrameLayout.LayoutParams.FILL_PARENT));

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //这里可拦截web的连接 进行相关操作
                //给个简单的例子
                if (url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                    Map<String, String> extraHeaders = new HashMap<String, String>();
                    extraHeaders.put("Referer", "");
                    view.loadUrl(url, extraHeaders);
                    return false;
                } else {
                    if (url.contains("jxz://h5share")) { //这里拦截了url包换jxz://h5share 这段代码 然后从连接里面 找到sharedesc 等的一些参数 可以做相关操作
                        Uri uri = Uri.parse(url);
                        String sharedesc = uri.getQueryParameter("sharedesc");
                    }
                }
                return true;
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });


        WebSettings webSetting = mWebView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        // webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        // webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0)
                .getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        long time = System.currentTimeMillis();
        mWebView.loadUrl(mIntentUrl);
        TbsLog.d("time-cost", "cost time: "
                + (System.currentTimeMillis() - time));
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();

        return null;
    }
}
