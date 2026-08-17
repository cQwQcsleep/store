package com.android.tools.r8.internal;

import defpackage.pv9;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ov, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0733Ov implements Iterator {
    public final Iterator b;
    public int c;

    public C0733Ov(Iterator it) {
        KB.c(it, "iterator");
        this.b = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.c;
        this.c = i + 1;
        if (i >= 0) {
            return new C0681Mv(i, this.b.next());
        }
        pv9.a("Index overflow has happened.");
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
