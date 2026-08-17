package com.intellij.util.xmlb;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface Accessor {
    <T extends Annotation> T getAnnotation(Class<T> cls);

    Type getGenericType();

    String getName();

    Class<?> getValueClass();

    <T extends Annotation> boolean isAnnotationPresent(Class<T> cls);

    boolean isWritable();

    Object read(Object obj);
}
