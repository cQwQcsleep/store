package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0175b1;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.i50, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1717i50 {
    public final AbstractC0175b1 a;
    public final Set b = C1755ib0.a();

    public C1717i50(AbstractC0175b1 abstractC0175b1) {
        this.a = abstractC0175b1;
    }

    public final String toString() {
        return "Node(" + this.a.getReference().m0() + ")";
    }
}
