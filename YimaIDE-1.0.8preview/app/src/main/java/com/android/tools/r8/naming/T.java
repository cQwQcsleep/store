package com.android.tools.r8.naming;

import com.android.tools.r8.internal.C2752uB;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class T {
    public static final /* synthetic */ boolean a = true;

    public static String a(C3313b... c3313bArr) throws Throwable {
        C2752uB c2752uB = new C2752uB();
        if (!a && c3313bArr.length <= 0) {
            x1f.a();
            return null;
        }
        C3352v c3352v = new C3352v(c2752uB);
        for (C3313b c3313b : c3313bArr) {
            c3352v.a(c3313b);
        }
        return c3352v.a();
    }
}
