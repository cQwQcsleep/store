package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.C0322w2;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class N {
    public final C0322w2 a;
    public final boolean b;

    public N(C0322w2 c0322w2, boolean z) {
        this.a = c0322w2;
        this.b = z;
    }

    public final boolean equals(Object obj) {
        if (obj != null && N.class == obj.getClass()) {
            N n = (N) obj;
            if (this.a == n.a && this.b == n.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, Boolean.valueOf(this.b));
    }
}
