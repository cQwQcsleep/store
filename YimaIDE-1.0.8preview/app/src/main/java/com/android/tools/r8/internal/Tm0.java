package com.android.tools.r8.internal;

import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Tm0 implements InterfaceC1247cd0 {
    public final List a;
    public final List b;
    public final boolean c;

    public Tm0(List list, List list2, boolean z) {
        this.a = list;
        this.b = list2;
        this.c = z;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1247cd0
    public final Object[] a(IP ip) {
        ip.getClass();
        return new Object[]{ip.a(this.a), Boolean.valueOf(this.c), ip.a(a())};
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tm0)) {
            return false;
        }
        Tm0 tm0 = (Tm0) obj;
        return this.c == tm0.c && Objects.equals(this.a, tm0.a) && Objects.equals(this.b, tm0.b);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b, Boolean.valueOf(this.c));
    }

    public List<com.android.tools.r8.graph.I2> a() {
        return this.b;
    }
}
