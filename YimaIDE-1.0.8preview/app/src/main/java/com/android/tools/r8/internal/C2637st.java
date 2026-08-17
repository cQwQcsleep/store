package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.st, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2637st {
    public final com.android.tools.r8.graph.I2 a;
    public final Set b;

    public C2637st(com.android.tools.r8.graph.I2 i2, Set set) {
        this.a = i2;
        this.b = set;
    }

    public C2637st a(C2637st c2637st) {
        com.android.tools.r8.graph.I2 i2 = this.a;
        com.android.tools.r8.graph.I2 i3 = c2637st.a;
        i2.getClass();
        if (!com.android.tools.r8.graph.I2.a(i2, i3)) {
            c41.a("Emulated interface descriptor can only be merged on the same rewritten type.");
            return null;
        }
        int i = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        c1870jv.a((Iterable) this.b);
        c1870jv.a((Iterable) c2637st.b);
        return new C2637st(this.a, c1870jv.a());
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C2637st)) {
            return false;
        }
        C2637st c2637st = (C2637st) obj;
        com.android.tools.r8.graph.I2 i2 = this.a;
        com.android.tools.r8.graph.I2 i3 = c2637st.a;
        i2.getClass();
        return com.android.tools.r8.graph.I2.a(i2, i3) && this.b.equals(c2637st.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 7);
    }

    public boolean a(com.android.tools.r8.graph.B1 b1, C0322w2 c0322w2) {
        return this.b.contains(c0322w2);
    }
}
