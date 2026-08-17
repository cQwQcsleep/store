package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Qs, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0782Qs implements Iterator {
    public C0627Ks b;
    public C0627Ks c = null;
    public int d;
    public int e;
    public final /* synthetic */ C0860Ts f;

    public AbstractC0782Qs(C0860Ts c0860Ts) {
        this.f = c0860Ts;
        this.b = c0860Ts.d;
        this.d = c0860Ts.h;
        this.e = c0860Ts.f;
    }

    public abstract Object a(C0627Ks c0627Ks);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f.h == this.d) {
            return this.b != null && this.e > 0;
        }
        a1e.a();
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        C0627Ks c0627Ks = this.b;
        Objects.requireNonNull(c0627Ks);
        this.b = c0627Ks.h;
        this.c = c0627Ks;
        this.e--;
        return a(c0627Ks);
    }

    @Override // java.util.Iterator
    public final void remove() {
        C0860Ts c0860Ts = this.f;
        if (c0860Ts.h != this.d) {
            a1e.a();
            return;
        }
        C0627Ks c0627Ks = this.c;
        if (c0627Ks == null) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        c0860Ts.a(c0627Ks);
        this.d = this.f.h;
        this.c = null;
    }
}
