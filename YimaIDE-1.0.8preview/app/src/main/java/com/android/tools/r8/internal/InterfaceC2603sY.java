package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.InterfaceC0332x5;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sY, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC2603sY {
    default InterfaceC2603sY a(com.android.tools.r8.graph.F2 f2) {
        f2.getClass();
        if (f2 instanceof com.android.tools.r8.graph.I2) {
            return a(f2.r0());
        }
        if (AbstractC2433qY.a || f2.u0()) {
            return a(f2.q0());
        }
        x1f.a();
        return null;
    }

    InterfaceC2603sY a(com.android.tools.r8.graph.I2 i2);

    InterfaceC2603sY a(C0322w2 c0322w2);

    void a(com.android.tools.r8.graph.B5 b5, C0322w2 c0322w2);

    default InterfaceC2603sY a(InterfaceC0332x5 interfaceC0332x5) {
        return a(interfaceC0332x5.getReference());
    }
}
