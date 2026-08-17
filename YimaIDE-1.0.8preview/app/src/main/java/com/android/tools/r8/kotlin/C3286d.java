package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3286d {
    public final C0322w2 a;
    public final C0322w2 b;
    public final C0322w2 c;
    public final C0322w2 d;

    public C3286d(C3289g c3289g) {
        I2 i2E = c3289g.a.e("Lkotlin/jvm/internal/Intrinsics;");
        B1 b1 = c3289g.a;
        this.a = b1.a(i2E, b1.a(b1.E1, b1.Y1), "throwParameterIsNullException");
        B1 b2 = c3289g.a;
        this.b = b2.a(i2E, b2.a(b2.E1, b2.Y1), "throwParameterIsNullNPE");
        B1 b3 = c3289g.a;
        b3.a(i2E, b3.a(b3.E1, b3.Y1), "throwParameterIsNullIAE");
        B1 b4 = c3289g.a;
        this.c = b4.a(i2E, b4.a(b4.E1, b4.a2, b4.Y1), "checkParameterIsNotNull");
        B1 b5 = c3289g.a;
        this.d = b5.a(i2E, b5.a(b5.E1, b5.a2, b5.Y1), "checkNotNullParameter");
        B1 b6 = c3289g.a;
        b6.a(i2E, b6.a(b6.E1, new I2[0]), "throwNpe");
    }
}
