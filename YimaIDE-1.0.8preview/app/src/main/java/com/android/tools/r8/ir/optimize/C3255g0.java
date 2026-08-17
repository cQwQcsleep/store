package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.KN;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.g0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3255g0 extends AbstractC3253f0 {
    public final int c;

    public C3255g0(C2543rl0 c2543rl0, int i, KN kn) {
        super(c2543rl0, kn);
        this.c = i;
    }

    @Override // com.android.tools.r8.ir.optimize.AbstractC3253f0
    public final boolean a(int i) {
        return this.c == i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C3255g0.class == obj.getClass()) {
            C3255g0 c3255g0 = (C3255g0) obj;
            if (this.c == c3255g0.c && this.a == c3255g0.a && this.b == c3255g0.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, Integer.valueOf(this.c), this.b);
    }
}
