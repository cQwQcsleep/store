package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class WW {
    public static final /* synthetic */ int a = 0;

    static {
        Logger.getLogger(WW.class.getName());
    }

    public static Object[] a(int i, Object[] objArr) {
        if (objArr.length != 0) {
            objArr = Arrays.copyOf(objArr, 0);
        }
        return Arrays.copyOf(objArr, i);
    }

    public static Set a() {
        return ConcurrentHashMap.newKeySet();
    }

    public static Object[] a(int i, int i2, Object[] objArr, Object[] objArr2) {
        return Arrays.copyOfRange(objArr, i, i2, objArr2.getClass());
    }
}
