package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0333y;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ON {
    com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3);

    void a(C0333y c0333y);

    boolean a(com.android.tools.r8.graph.I2 i2);

    boolean b(com.android.tools.r8.graph.I2 i2);

    default boolean c(com.android.tools.r8.graph.I2 i2) {
        return b(i2) || a(i2);
    }
}
