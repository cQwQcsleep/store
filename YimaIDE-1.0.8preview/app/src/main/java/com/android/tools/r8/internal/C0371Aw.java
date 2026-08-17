package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C0371Aw;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Aw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0371Aw {
    public static final /* synthetic */ boolean g = true;
    public final com.android.tools.r8.graph.B1 a;
    public final com.android.tools.r8.graph.K2 b;
    public final LinkedHashMap c = new LinkedHashMap();
    public final LinkedHashMap d = new LinkedHashMap();
    public C0322w2 e;
    public List f;

    public C0371Aw(final com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        this.a = b1;
        com.android.tools.r8.graph.K2 k2B0 = c0322w2.B0();
        Function function = new Function() { // from class: rj0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C0371Aw.a(b1, (I2) obj);
            }
        };
        if (k2B0.isEmpty()) {
            k2B0 = com.android.tools.r8.graph.K2.n0();
        } else {
            com.android.tools.r8.graph.I2[] i2Arr = (com.android.tools.r8.graph.I2[]) R3.a((Object[]) k2B0.b, function, (Object[]) com.android.tools.r8.graph.I2.h);
            if (i2Arr != k2B0.b) {
                k2B0 = i2Arr.length == 0 ? com.android.tools.r8.graph.K2.n0() : new com.android.tools.r8.graph.K2(i2Arr);
            }
        }
        this.b = k2B0;
    }

    public static /* synthetic */ com.android.tools.r8.graph.I2 a(com.android.tools.r8.graph.B1 b1, com.android.tools.r8.graph.I2 i2) {
        return i2.T0() ? i2 : b1.a2;
    }
}
