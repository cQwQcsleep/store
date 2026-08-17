package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Stack;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ra0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2521ra0 implements Iterator {
    public final Stack b = new Stack();
    public CL c;

    public C2521ra0(T7 t7) {
        while (t7 instanceof C2692ta0) {
            C2692ta0 c2692ta0 = (C2692ta0) t7;
            this.b.push(c2692ta0);
            t7 = c2692ta0.e;
        }
        this.c = (CL) t7;
    }

    @Override // java.util.Iterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final CL next() {
        CL cl = this.c;
        CL cl2 = null;
        if (cl == null) {
            z0e.a();
            return null;
        }
        while (!this.b.isEmpty()) {
            T7 t7 = ((C2692ta0) this.b.pop()).f;
            while (t7 instanceof C2692ta0) {
                C2692ta0 c2692ta0 = (C2692ta0) t7;
                this.b.push(c2692ta0);
                t7 = c2692ta0.e;
            }
            CL cl3 = (CL) t7;
            if (cl3.d.length != 0) {
                cl2 = cl3;
                break;
            }
        }
        this.c = cl2;
        return cl;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c != null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
