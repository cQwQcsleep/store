package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.sX, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2602sX {
    public final com.android.tools.r8.graph.J0 a;
    public final int b;
    public final int c;

    public C2602sX(com.android.tools.r8.graph.J0 j0, int i, int i2) {
        this.a = j0;
        this.b = i;
        this.c = i2;
    }

    public final boolean equals(Object obj) {
        C2602sX c2602sX = (C2602sX) obj;
        return this.b == c2602sX.b && this.c == c2602sX.c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.b), Integer.valueOf(this.c));
    }
}
