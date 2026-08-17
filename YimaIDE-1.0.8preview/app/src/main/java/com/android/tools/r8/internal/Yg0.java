package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface Yg0 {
    default void a() {
    }

    void a(Ch0 ch0);

    default void b(Ch0 ch0) {
        try {
            a(ch0);
        } catch (Exception e) {
            rc6.a(e);
        }
    }

    default boolean b() {
        return true;
    }
}
