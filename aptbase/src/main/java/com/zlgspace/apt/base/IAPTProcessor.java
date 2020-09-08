package com.zlgspace.apt.base;

import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

public interface IAPTProcessor {

    void init(ProcessingEnvironment processingEnv);

    Set<String> getSupportedAnnotationTypes();

    boolean process(Set<? extends TypeElement> var1, RoundEnvironment var2);
}
