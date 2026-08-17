package com.android.tools.r8.internal;

import defpackage.do4;
import defpackage.vye;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ud, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0871Ud {
    public static void a(Object obj, Object obj2) {
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        }
        if (obj2 != null) {
            return;
        }
        vye.a("null value in entry: ", obj, "=null");
    }

    public static void a(int i, String str) {
        if (i >= 0) {
            return;
        }
        do4.a(str, " cannot be negative but was: ", i);
    }
}
