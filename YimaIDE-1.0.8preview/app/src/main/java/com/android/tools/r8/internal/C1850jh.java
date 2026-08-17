package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1850jh implements InterfaceC1247cd0 {
    public static final /* synthetic */ boolean c = true;
    public final C0322w2 a;
    public final C0322w2 b;

    public C1850jh(C0322w2 c0322w2, C0322w2 c0322w3) {
        this.a = c0322w2;
        this.b = c0322w3;
        boolean z = c;
        if (!z && c0322w2.D0() != c0322w3.k(0)) {
            x1f.a();
            throw null;
        }
        if (z || c0322w3.D0() == c0322w2.k(0)) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1247cd0
    public final Object[] a(IP ip) {
        ip.getClass();
        return new Object[]{ip.a(this.a), ip.a(this.b)};
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1850jh)) {
            return false;
        }
        C1850jh c1850jh = (C1850jh) obj;
        return this.a == c1850jh.a && this.b == c1850jh.b;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
