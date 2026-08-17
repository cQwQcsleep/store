package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2474r0 implements Iterator {
    public final Iterator b;
    public Object c = null;
    public Collection d = null;
    public Iterator e = LC.b;
    public final /* synthetic */ AbstractC3157z0 f;

    public AbstractC2474r0(AbstractC3157z0 abstractC3157z0) {
        this.f = abstractC3157z0;
        this.b = abstractC3157z0.f.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext() || this.e.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.e.hasNext()) {
            Map.Entry entry = (Map.Entry) this.b.next();
            this.c = entry.getKey();
            Collection collection = (Collection) entry.getValue();
            this.d = collection;
            this.e = collection.iterator();
        }
        return new C3236zu(this.c, this.e.next());
    }

    @Override // java.util.Iterator
    public final void remove() {
        this.e.remove();
        Collection collection = this.d;
        Objects.requireNonNull(collection);
        if (collection.isEmpty()) {
            this.b.remove();
        }
        this.f.g--;
    }
}
