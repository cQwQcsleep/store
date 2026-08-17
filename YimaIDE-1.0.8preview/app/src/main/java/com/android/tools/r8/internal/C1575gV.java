package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0217h1;
import com.android.tools.r8.internal.C1575gV;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gV, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1575gV extends AbstractC1757ic0 {
    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        if (d2.f1()) {
            return false;
        }
        return !AbstractC3179zC.b(d2.B1(), new EX() { // from class: qxg
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return C1575gV.a((AbstractC0217h1) obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "OnlyStaticDefinitions";
    }

    public static /* synthetic */ boolean a(AbstractC0217h1 abstractC0217h1) {
        return !abstractC0217h1.z0();
    }
}
