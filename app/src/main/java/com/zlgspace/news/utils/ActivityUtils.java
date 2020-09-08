package com.zlgspace.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActivityUtils {

    public static void toActivity(Context context, Class<? extends Activity> clz){
        Intent intent = new Intent(context,clz);
        context.startActivity(intent);
    }

    public static void toNewsDetailActivity(Context context, Class<? extends Activity> clz, String url, String title){
        Intent intent = new Intent(context,clz);
        intent.putExtra("URL",url);
        intent.putExtra("TITLE",title);
        context.startActivity(intent);
    }
}
