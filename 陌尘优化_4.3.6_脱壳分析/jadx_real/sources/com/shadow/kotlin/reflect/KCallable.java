package com.shadow.kotlin.reflect;

import java.util.List;
import java.util.Map;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface KCallable<R> extends kotlin.reflect.KAnnotatedElement {
    R call(Object... objArr);

    R callBy(Map<Object, ? extends Object> map);

    List<Object> getParameters();

    KType getReturnType();

    List<Object> getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
