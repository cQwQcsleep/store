package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0205f3;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Fa0 {
    public final C0205f3 a;
    public final com.android.tools.r8.graph.I2 b;

    public Fa0(C0205f3 c0205f3, com.android.tools.r8.graph.I2 i2) {
        C0205f3 c0205f3F = C0205f3.f(c0205f3.c);
        c0205f3F.c(16);
        C0205f3 c0205f4 = (C0205f3) c0205f3F.R();
        c0205f4.c(4096);
        this.a = (C0205f3) c0205f4.R();
        this.b = i2;
    }

    public final boolean equals(Object obj) {
        if (obj != null && Fa0.class == obj.getClass()) {
            Fa0 fa0 = (Fa0) obj;
            if (this.a.c == fa0.a.c && this.b == fa0.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
