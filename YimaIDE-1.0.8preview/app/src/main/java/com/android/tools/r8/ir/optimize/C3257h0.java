package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.KN;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.h0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3257h0 extends AbstractC3253f0 {
    public final C2543rl0 c;

    public C3257h0(KN kn, C2543rl0 c2543rl0, C2543rl0 c2543rl1) {
        super(c2543rl0, kn);
        this.c = c2543rl1;
    }

    @Override // com.android.tools.r8.ir.optimize.AbstractC3253f0
    public final boolean a(int i) {
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && C3257h0.class == obj.getClass()) {
            C3257h0 c3257h0 = (C3257h0) obj;
            if (this.c == c3257h0.c && this.a == c3257h0.a && this.b == c3257h0.b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.c, this.b);
    }
}
