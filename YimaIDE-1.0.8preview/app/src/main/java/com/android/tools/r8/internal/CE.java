package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CE {
    public static final CE c = new CE(C2245oH.a, 1);
    public static final /* synthetic */ boolean d = true;
    public final AbstractC2587sH a;
    public final int b;

    public CE(AbstractC2587sH abstractC2587sH, int i) {
        boolean z = d;
        if (!z && abstractC2587sH == null) {
            x1f.a();
            throw null;
        }
        if (!z && i <= 0) {
            x1f.a();
            throw null;
        }
        this.a = abstractC2587sH;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CE)) {
            return false;
        }
        CE ce = (CE) obj;
        return this.b == ce.b && Objects.equals(this.a, ce.a);
    }

    public final int hashCode() {
        return Objects.hash(this.a, Integer.valueOf(this.b));
    }

    public final String toString() {
        return this.a + Xf0.a(this.b);
    }
}
