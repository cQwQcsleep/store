package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yp, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0987Yp implements Iterator {
    public final Iterator b;
    public int c = -1;
    public Object d;
    public final /* synthetic */ C1013Zp e;

    public C0987Yp(C1013Zp c1013Zp) {
        this.e = c1013Zp;
        this.b = c1013Zp.a.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.c == -1) {
            while (true) {
                if (!this.b.hasNext()) {
                    this.c = 0;
                    break;
                }
                Object next = this.b.next();
                if (((Boolean) this.e.c.b(next)).booleanValue() == this.e.b) {
                    this.d = next;
                    this.c = 1;
                    break;
                }
            }
        }
        return this.c == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.c == -1) {
            while (true) {
                if (!this.b.hasNext()) {
                    this.c = 0;
                    break;
                }
                Object next = this.b.next();
                if (((Boolean) this.e.c.b(next)).booleanValue() == this.e.b) {
                    this.d = next;
                    this.c = 1;
                    break;
                }
            }
        }
        if (this.c == 0) {
            z0e.a();
            return null;
        }
        Object obj = this.d;
        this.d = null;
        this.c = -1;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
