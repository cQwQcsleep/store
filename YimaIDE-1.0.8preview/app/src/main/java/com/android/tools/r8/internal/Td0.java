package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Td0 {
    public final com.android.tools.r8.graph.I2 a;
    public final com.android.tools.r8.graph.I2 b;
    public final C0322w2 c;
    public final C0322w2 d;

    public Td0(com.android.tools.r8.graph.B1 b1) {
        this.a = b1.e("Lcom/android/tools/r8/startup/InstrumentationServer;");
        com.android.tools.r8.graph.I2 i2E = b1.e("Lcom/android/tools/r8/startup/InstrumentationServerImpl;");
        this.b = i2E;
        com.android.tools.r8.graph.I2 i2 = b1.E1;
        com.android.tools.r8.graph.I2 i3 = b1.Y1;
        this.c = b1.a(i2E, b1.a(i2, i3, i3), "addCall");
        this.d = b1.a(i2E, b1.a(b1.E1, b1.Y1), "addMethod");
    }
}
