package com.zlgspace.aptmodule;

import com.zlgspace.apt.base.IAPTProcessor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

final class APT {
    private APT(){}

    static void init(ProcessingEnvironment var1){
        for(IAPTProcessor processor: APTModuleConfig.PROCESSORS){
            processor.init(var1);
        }
    }

    static Set<String> getSupportedAnnotationTypes(){
        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(IAPTProcessor processor: APTModuleConfig.PROCESSORS){
            set.addAll(processor.getSupportedAnnotationTypes());
        }
        return set;
    }

    static boolean process(Set<? extends TypeElement> var1, RoundEnvironment var2){
        for(IAPTProcessor processor: APTModuleConfig.PROCESSORS){
            processor.process(var1,var2);
        }
        return false;
    }


}
