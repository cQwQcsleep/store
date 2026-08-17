package com.android.tools.r8.internal;

import com.android.tools.r8.graph.AbstractC0259n1;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.in, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1777in implements InterfaceC1247cd0 {
    public final com.android.tools.r8.graph.I2 a;
    public final Map b;

    public C1777in(com.android.tools.r8.graph.I2 i2, IdentityHashMap identityHashMap) {
        this.a = i2;
        this.b = identityHashMap;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1247cd0
    public final Object[] a(IP ip) {
        ip.getClass();
        return new Object[]{ip.a((AbstractC0259n1) this.a), ip.b(this.b)};
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1777in)) {
            return false;
        }
        C1777in c1777in = (C1777in) obj;
        return this.a == c1777in.a && this.b.equals(c1777in.b);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
