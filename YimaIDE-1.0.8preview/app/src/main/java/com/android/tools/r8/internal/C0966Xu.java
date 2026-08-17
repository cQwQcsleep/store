package com.android.tools.r8.internal;

import java.util.Map;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0966Xu extends Ck0 {
    public final Ck0 b;
    public Object c = null;
    public Ck0 d = JC.f;

    public C0966Xu(AbstractC1105av abstractC1105av) {
        this.b = abstractC1105av.f.entrySet().iterator();
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.d.hasNext() || this.b.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.d.hasNext()) {
            Map.Entry entry = (Map.Entry) this.b.next();
            this.c = entry.getKey();
            this.d = ((AbstractC3066xu) entry.getValue()).iterator();
        }
        Object obj = this.c;
        Objects.requireNonNull(obj);
        return new C3236zu(obj, this.d.next());
    }
}
