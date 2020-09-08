package com.zlgspace.news.msgparser;


import android.os.Handler;

import androidx.annotation.UiThread;

import com.zlgspace.msgpraser.base.CallbackMsg;
import com.zlgspace.msgpraser.base.MsgParserAdapter;
import com.zlgspace.news.utils.GsonUtils;

/**
 * 消息解析适配器
 */
public class ParserAdapter extends MsgParserAdapter<CallbackMsg,String> {

    private Handler handler;

    @UiThread
    public ParserAdapter(){
        handler = new Handler();
    }

    @Override
    public CallbackMsg preParser(CallbackMsg callbackMsg) {
        return callbackMsg;
    }

    @Override
    public Object parser(String s, Class aClass) {
        return GsonUtils.fromJson(s,aClass);
    }

    @Override
    public boolean dispatchRstToMainThread(Runnable runable) {
        return handler.post(runable);
    }
}
