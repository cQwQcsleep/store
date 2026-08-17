package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.xC, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3010xC implements Iterator {
    public boolean b = true;
    public final /* synthetic */ Iterator c;

    public C3010xC(Iterator it) {
        this.c = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object next = this.c.next();
        this.b = false;
        return next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.b) {
            k2d.a("no calls to next() since the last call to remove()");
        } else {
            this.c.remove();
        }
    }
}
