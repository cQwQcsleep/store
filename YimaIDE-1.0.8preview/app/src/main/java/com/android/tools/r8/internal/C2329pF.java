package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.pF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2329pF {
    public C3097yF a = C3097yF.c;
    public IE b = IE.b;
    public AbstractC1222cH c = ZG.a;
    public SE d;

    public final C2415qF a() {
        if (!this.d.a.isEmpty()) {
            return new C2415qF(this.a, this.b, this.c, this.d);
        }
        defpackage.l0.a("KeepEdge must have non-empty set of consequences.");
        return null;
    }

    public final C2329pF a(IE ie) {
        this.b = ie;
        return this;
    }

    public final C2329pF a(AbstractC1222cH abstractC1222cH) {
        this.c = abstractC1222cH;
        return this;
    }

    public final C2329pF a(SE se) {
        this.d = se;
        return this;
    }

    public final C2329pF a(C3097yF c3097yF) {
        this.a = c3097yF;
        return this;
    }
}
