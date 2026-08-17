package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class D0 implements Iterator {
    public final Iterator b;
    public Map.Entry c;
    public int d;
    public boolean e;
    public final /* synthetic */ E0 f;

    public D0(E0 e0) {
        this.f = e0;
        this.b = e0.d.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d > 0 || this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.d == 0) {
            Map.Entry entry = (Map.Entry) this.b.next();
            this.c = entry;
            this.d = ((C1254ch) entry.getValue()).b;
        }
        this.d--;
        this.e = true;
        Map.Entry entry2 = this.c;
        Objects.requireNonNull(entry2);
        return entry2.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!this.e) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        Map.Entry entry = this.c;
        Objects.requireNonNull(entry);
        if (((C1254ch) entry.getValue()).b <= 0) {
            a1e.a();
            return;
        }
        C1254ch c1254ch = (C1254ch) this.c.getValue();
        int i = c1254ch.b - 1;
        c1254ch.b = i;
        if (i == 0) {
            this.b.remove();
        }
        this.f.e--;
        this.e = false;
    }
}
