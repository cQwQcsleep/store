package com.android.tools.r8.internal;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2162nK implements Iterator {
    public final HashSet b;
    public C2334pK c;
    public C2334pK d;
    public int e;
    public final /* synthetic */ C2590sK f;

    public C2162nK(C2590sK c2590sK) {
        this.f = c2590sK;
        this.b = new HashSet(AbstractC1739iN.a(c2590sK.keySet().size()));
        this.c = c2590sK.f;
        this.e = c2590sK.j;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f.j == this.e) {
            return this.c != null;
        }
        a1e.a();
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C2334pK c2334pK;
        if (this.f.j != this.e) {
            a1e.a();
            return null;
        }
        C2334pK c2334pK2 = this.c;
        if (c2334pK2 == null) {
            z0e.a();
            return null;
        }
        this.d = c2334pK2;
        this.b.add(c2334pK2.b);
        do {
            c2334pK = this.c.d;
            this.c = c2334pK;
            if (c2334pK == null) {
                break;
            }
        } while (!this.b.add(c2334pK.b));
        return this.d.b;
    }

    @Override // java.util.Iterator
    public final void remove() {
        C2590sK c2590sK = this.f;
        if (c2590sK.j != this.e) {
            a1e.a();
            return;
        }
        C2334pK c2334pK = this.d;
        if (!(c2334pK != null)) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        C2504rK c2504rK = new C2504rK(c2590sK, c2334pK.b);
        while (c2504rK.hasNext()) {
            c2504rK.next();
            c2504rK.remove();
        }
        this.d = null;
        this.e = this.f.j;
    }
}
