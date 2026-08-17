package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0983Yl {
    public static String a(int i) {
        if (i == 0) {
            return "classes.dex";
        }
        return "classes" + (i + 1) + ".dex";
    }
}
