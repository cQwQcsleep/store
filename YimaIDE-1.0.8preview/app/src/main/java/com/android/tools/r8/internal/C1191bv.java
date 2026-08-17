package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1191bv extends Ck0 {
    public int b;
    public Object c;
    public final /* synthetic */ Iterator d;

    public C1191bv(Ck0 ck0) {
        this.d = ck0;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b > 0 || this.d.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.b <= 0) {
            AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) this.d.next();
            this.c = abstractC1314dQ.b();
            this.b = abstractC1314dQ.a();
        }
        this.b--;
        Object obj = this.c;
        Objects.requireNonNull(obj);
        return obj;
    }
}
