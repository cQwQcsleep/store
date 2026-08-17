package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2816v0 implements Iterator {
    public final Iterator b;
    public final Collection c;
    public final /* synthetic */ AbstractC2902w0 d;

    public C2816v0(AbstractC2902w0 abstractC2902w0) {
        this.d = abstractC2902w0;
        Collection collection = abstractC2902w0.c;
        this.c = collection;
        this.b = collection instanceof List ? ((List) collection).listIterator() : collection.iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        this.d.b();
        if (this.d.c == this.c) {
            return this.b.hasNext();
        }
        a1e.a();
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        this.d.b();
        if (this.d.c == this.c) {
            return this.b.next();
        }
        a1e.a();
        return null;
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.b.remove();
        AbstractC2902w0 abstractC2902w0 = this.d;
        abstractC2902w0.f.g--;
        abstractC2902w0.c();
    }

    public C2816v0(AbstractC2902w0 abstractC2902w0, ListIterator listIterator) {
        this.d = abstractC2902w0;
        this.c = abstractC2902w0.c;
        this.b = listIterator;
    }
}
