package com.android.tools.r8.internal;

import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1870jv extends AbstractC2981wu {
    public AbstractC2469qv a = C2041lv.c;
    public boolean b;

    public final C1870jv a(C1870jv c1870jv) {
        Objects.requireNonNull(this.a);
        Objects.requireNonNull(c1870jv.a);
        if (this.b) {
            Objects.requireNonNull(this.a);
            this.a = this.a.b();
            this.b = false;
        }
        AbstractC2469qv abstractC2469qvA = this.a;
        AbstractC2469qv abstractC2469qv = c1870jv.a;
        abstractC2469qvA.getClass();
        for (int i = 0; i < abstractC2469qv.b; i++) {
            Object obj = abstractC2469qv.a[i];
            Objects.requireNonNull(obj);
            abstractC2469qvA = abstractC2469qvA.a(obj);
        }
        this.a = abstractC2469qvA;
        return this;
    }

    @Override // com.android.tools.r8.internal.AbstractC2981wu
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final C1870jv a(Object obj) {
        Objects.requireNonNull(this.a);
        obj.getClass();
        if (this.b) {
            Objects.requireNonNull(this.a);
            this.a = this.a.b();
            this.b = false;
        }
        this.a = this.a.a(obj);
        return this;
    }

    public final C1870jv a(Set set) {
        a((Iterable) set);
        return this;
    }

    public final AbstractC2554rv a() {
        Objects.requireNonNull(this.a);
        this.b = true;
        AbstractC2469qv abstractC2469qvC = this.a.c();
        this.a = abstractC2469qvC;
        return abstractC2469qvC.a();
    }
}
