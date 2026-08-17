package com.android.tools.r8.graph;

import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.graph.f1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0203f1 extends AbstractC0259n1 {
    public final O2[] b;

    public C0203f1(O2[] o2Arr) {
        this.b = o2Arr;
    }

    @Override // com.android.tools.r8.graph.AbstractC0259n1
    public final void a(com.android.tools.r8.dex.X x) {
        x.a(this);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof C0203f1) && Arrays.equals(((C0203f1) obj).b, this.b);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.b);
    }

    public final String toString() {
        return "EncodedArray " + Arrays.toString(this.b);
    }
}
