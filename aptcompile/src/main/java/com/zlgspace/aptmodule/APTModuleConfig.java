package com.zlgspace.aptmodule;


import com.zlgspace.apt.base.IAPTProcessor;
import com.zlgspace.msgpraser.MessageParserProcessor;
import com.zlgspace.singleinstancehelper.SingleInstanceProcessor;

final class APTModuleConfig {
    private APTModuleConfig(){}

    /**
     * You need to manually configure the IAPTProcessor
     * implementation class in the corresponding JAR package here
     */
    public static final IAPTProcessor[] PROCESSORS = new IAPTProcessor[]{
            new SingleInstanceProcessor(),//singleinstancehellper
            new MessageParserProcessor(),//msgpraser
    };
}
