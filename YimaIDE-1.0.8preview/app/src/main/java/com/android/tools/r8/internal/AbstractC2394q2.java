package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2394q2 {
    public static final Class a;
    public static final boolean b;

    static {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            cls = Class.forName("libcore.io.Memory");
        } catch (Throwable unused) {
            cls = null;
        }
        a = cls;
        try {
            cls2 = Class.forName("org.robolectric.Robolectric");
        } catch (Throwable unused2) {
        }
        b = cls2 != null;
    }

    public static Class a() {
        return a;
    }

    public static boolean b() {
        return (a == null || b) ? false : true;
    }
}
