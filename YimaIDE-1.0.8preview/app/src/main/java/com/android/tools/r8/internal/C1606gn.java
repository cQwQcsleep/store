package com.android.tools.r8.internal;

import java.util.LinkedHashMap;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gn, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1606gn implements InterfaceC1247cd0 {
    public final C2964wi a;
    public final C2964wi b;
    public final C2964wi c;
    public final LinkedHashMap d;

    public C1606gn(C2964wi c2964wi, C2964wi c2964wi2, C2964wi c2964wi3, LinkedHashMap linkedHashMap) {
        this.a = c2964wi;
        this.b = c2964wi2;
        this.c = c2964wi3;
        this.d = linkedHashMap;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1247cd0
    public final Object[] a(IP ip) {
        ip.getClass();
        return new Object[]{ip.a(this.a), ip.a(this.b), ip.a(this.c), ip.a(this.d)};
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1606gn)) {
            return false;
        }
        C1606gn c1606gn = (C1606gn) obj;
        return Objects.equals(this.a, c1606gn.a) && Objects.equals(this.b, c1606gn.b) && Objects.equals(this.c, c1606gn.c) && Objects.equals(this.d, c1606gn.d);
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b, this.c, this.d);
    }
}
