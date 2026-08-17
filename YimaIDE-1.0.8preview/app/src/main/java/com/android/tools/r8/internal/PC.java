package com.android.tools.r8.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class PC extends ZW {
    public final void a(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        KB.c(th, "cause");
        Integer num = OC.a;
        if (num == null || num.intValue() >= 19) {
            th.addSuppressed(th2);
            return;
        }
        Method method = YW.a;
        if (method != null) {
            method.invoke(th, th2);
        }
    }
}
