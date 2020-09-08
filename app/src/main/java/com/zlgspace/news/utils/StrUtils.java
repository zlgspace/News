package com.zlgspace.news.utils;

import android.annotation.TargetApi;
import android.os.Build;

public class StrUtils {

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static boolean isEmpty(String dst){
        return dst.isEmpty();
    }

    public static boolean isNull(String dst){
        if(dst == null){
            return true;
        }
        return false;
    }

    public static boolean isEmptyOrNull(String dst){
        return isNull(dst)||isEmpty(dst);
    }
}
