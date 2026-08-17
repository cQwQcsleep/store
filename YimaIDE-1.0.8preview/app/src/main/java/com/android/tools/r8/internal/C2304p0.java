package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2304p0 implements Iterator {
    public final Iterator b;
    public Collection c;
    public final /* synthetic */ C2390q0 d;

    public C2304p0(C2390q0 c2390q0) {
        this.d = c2390q0;
        this.b = c2390q0.d.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Map.Entry entry = (Map.Entry) this.b.next();
        this.c = (Collection) entry.getValue();
        return this.d.a(entry);
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (!(this.c != null)) {
            k2d.a("no calls to next() since the last call to remove()");
            return;
        }
        this.b.remove();
        this.d.e.g -= this.c.size();
        this.c.clear();
        this.c = null;
    }
}
