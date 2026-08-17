package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FO implements B5 {
    public final C0322w2 a;
    public final int b;

    public FO(C0322w2 c0322w2, int i) {
        this.a = c0322w2;
        this.b = i;
    }

    public final boolean equals(Object obj) {
        if (obj != null && FO.class == obj.getClass()) {
            FO fo = (FO) obj;
            if (this.a == fo.a && this.b == fo.b) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    public final boolean h() {
        return true;
    }

    public final int hashCode() {
        return Objects.hash(this.a, Integer.valueOf(this.b));
    }

    @Override // com.android.tools.r8.internal.InterfaceC0370Av
    public final FO i() {
        return this;
    }

    public final String toString() {
        return "MethodParameter(" + this.a + ", " + this.b + ")";
    }
}
