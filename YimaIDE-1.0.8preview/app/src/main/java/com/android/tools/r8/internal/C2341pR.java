package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0210g1;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pR, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2341pR extends AbstractC1757ic0 {
    @Override // com.android.tools.r8.internal.AbstractC1757ic0
    public final boolean a(com.android.tools.r8.graph.D2 d2) {
        Iterator<C0210g1> it = d2.m1().iterator();
        while (it.hasNext()) {
            if (it.next().t0()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC1238cX
    public final String f() {
        return "NoInstanceFieldAnnotations";
    }
}
