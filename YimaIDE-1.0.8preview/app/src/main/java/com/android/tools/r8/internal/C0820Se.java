package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Se, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0820Se {
    public final com.android.tools.r8.graph.H2 a;
    public final com.android.tools.r8.graph.I2 b;
    public final com.android.tools.r8.graph.I2 c;
    public final C0322w2 d;

    public C0820Se(com.android.tools.r8.graph.B1 b1) {
        this.a = b1.c("$$changed");
        this.b = b1.e("Landroidx/compose/runtime/Composable;");
        this.c = b1.e("Landroidx/compose/runtime/Composer;");
        com.android.tools.r8.graph.I2 i2E = b1.e("Landroidx/compose/runtime/RecomposeScopeImplKt;");
        com.android.tools.r8.graph.I2 i2 = b1.B1;
        this.d = b1.a(i2E, b1.a(i2, i2), "updateChangedFlags");
    }

    public C0820Se(com.android.tools.r8.graph.H2 h2, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3, C0322w2 c0322w2) {
        this.a = h2;
        this.b = i2;
        this.c = i3;
        this.d = c0322w2;
    }
}
