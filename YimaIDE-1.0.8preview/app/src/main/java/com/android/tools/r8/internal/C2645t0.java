package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.t0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2645t0 extends AbstractC1482fN {
    public final /* synthetic */ AbstractC3157z0 c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2645t0(AbstractC3157z0 abstractC3157z0, Map map) {
        super(map);
        this.c = abstractC3157z0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1482fN, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        Iterator it = iterator();
        while (true) {
            C2560s0 c2560s0 = (C2560s0) it;
            if (!c2560s0.hasNext()) {
                return;
            }
            c2560s0.next();
            c2560s0.remove();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return this.b.keySet().containsAll(collection);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        return this == obj || this.b.keySet().equals(obj);
    }

    @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.b.keySet().hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2560s0(this, this.b.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        int size;
        Collection collection = (Collection) this.b.remove(obj);
        if (collection != null) {
            size = collection.size();
            collection.clear();
            this.c.g -= size;
        } else {
            size = 0;
        }
        return size > 0;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Spliterator spliterator() {
        return this.b.keySet().spliterator();
    }
}
