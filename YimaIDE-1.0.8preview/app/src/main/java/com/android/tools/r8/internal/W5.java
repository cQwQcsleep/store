package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface W5 extends V5 {
    default Object b(Object obj, Object obj2) {
        Object objC = c(obj);
        return objC != null ? objC : obj2;
    }

    Object c(Object obj);

    default Object c(Object obj, Object obj2) {
        Object objD = d(obj);
        return objD != null ? objD : obj2;
    }

    Object d(Object obj);
}
