package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zR, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3194zR extends AbstractC1757ic0 {
    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        return !AbstractC3179zC.b(d2.C1(), new EX() { // from class: h0j
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return ((C0231j1) obj).s1();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoNativeMethods";
    }
}
