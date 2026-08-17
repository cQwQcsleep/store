package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vq, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2891vq implements Iterator {
    public final Iterator b;
    public Iterator c;
    public final /* synthetic */ C2977wq d;

    public C2891vq(C2977wq c2977wq) {
        this.d = c2977wq;
        this.b = c2977wq.a.iterator();
    }

    public final boolean a() {
        Iterator it = this.c;
        if (it != null && !it.hasNext()) {
            this.c = null;
        }
        while (this.c == null) {
            if (!this.b.hasNext()) {
                return false;
            }
            Object next = this.b.next();
            C2977wq c2977wq = this.d;
            Iterator it2 = (Iterator) c2977wq.c.b(c2977wq.b.b(next));
            if (it2.hasNext()) {
                this.c = it2;
                break;
            }
        }
        return true;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return a();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!a()) {
            z0e.a();
            return null;
        }
        Iterator it = this.c;
        KB.a(it);
        return it.next();
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
