package com.zlgspace.news.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.util.Arrays;
import java.util.List;

public class GsonUtils {
    private static Gson gson = new Gson();

    public static String toJson(Object src) {
        return gson.toJson(src);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            return gson.fromJson(json, classOfT);
        } catch (JsonSyntaxException e) {
            throw new JsonSyntaxException(e);
        }
    }

    public static <T> List<T> jsonToList(String s, Class<T[]> cls) {
        T[] arr = new Gson().fromJson(s, cls);
        return Arrays.asList(arr);
    }
}
