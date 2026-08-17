package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ab, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1073ab {
    public static void a(com.android.tools.r8.graph.B1 b1) {
        synchronized (b1) {
            b1.a(b1.d(b1.c("Ljava/lang/ClassCastException;")));
        }
        b1.d("Ljava/lang/IllegalAccessError;");
        b1.d("Ljava/lang/IncompatibleClassChangeError;");
        b1.d("Ljava/lang/NoSuchMethodError;");
        b1.d("Ljava/lang/RuntimeException;");
    }
}
