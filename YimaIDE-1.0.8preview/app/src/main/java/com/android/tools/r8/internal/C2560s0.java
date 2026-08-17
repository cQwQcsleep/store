package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2560s0 implements Iterator {
    public Map.Entry b;
    public final /* synthetic */ Iterator c;
    public final /* synthetic */ C2645t0 d;

    public C2560s0(C2645t0 c2645t0, Iterator it) {
        this.d = c2645t0;
        this.c = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.c.next();
        this.b = entry;
        return entry.getKey();
    }

    @Override // java.util.Iterator
    public final void remove() {
        Map.Entry entry = this.b;
        if (!(entry != null)) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        Collection collection = (Collection) entry.getValue();
        this.c.remove();
        this.d.c.g -= collection.size();
        collection.clear();
        this.b = null;
    }
}
