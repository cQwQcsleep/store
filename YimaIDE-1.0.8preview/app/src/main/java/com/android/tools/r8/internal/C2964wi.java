package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0007c;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wi, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2964wi implements InterfaceC1247cd0 {
    public final C0322w2 a;
    public final int b;

    public C2964wi(C0322w2 c0322w2) {
        this.b = 0;
        this.a = c0322w2;
    }

    public final com.android.tools.r8.synthesis.S.b a(C0333y c0333y) {
        com.android.tools.r8.synthesis.S s = c0333y.a.g().b;
        int i = this.b;
        if (i == 0) {
            return null;
        }
        int iB = AbstractC0007c.b(i);
        if (iB == 0) {
            return s.m;
        }
        if (iB == 1) {
            return s.l;
        }
        if (iB == 2) {
            return s.j;
        }
        if (iB != 3) {
            return null;
        }
        return s.k;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2964wi)) {
            return false;
        }
        C2964wi c2964wi = (C2964wi) obj;
        return this.a == c2964wi.a && this.b == c2964wi.b;
    }

    public final int hashCode() {
        return Objects.hash(this.a, AbstractC0007c.a(this.b));
    }

    public C2964wi(C0322w2 c0322w2, int i) {
        this.b = i;
        this.a = c0322w2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1247cd0
    public final Object[] a(IP ip) {
        return ip.a(this);
    }
}
