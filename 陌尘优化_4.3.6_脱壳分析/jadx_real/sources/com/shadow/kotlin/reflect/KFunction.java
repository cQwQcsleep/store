package com.shadow.kotlin.reflect;

import kotlin.Function;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface KFunction<R> extends kotlin.reflect.KCallable<R>, Function<R> {
    boolean isExternal();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    boolean isSuspend();
}
