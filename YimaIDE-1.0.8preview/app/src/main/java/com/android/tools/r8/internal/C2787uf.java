package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2787uf extends AbstractC0910Vq implements Iterator {
    public AbstractC1314dQ b;
    public final /* synthetic */ Iterator c;
    public final /* synthetic */ C2958wf d;

    public C2787uf(C2958wf c2958wf, C2701tf c2701tf) {
        this.d = c2958wf;
        this.c = c2701tf;
    }

    @Override // com.android.tools.r8.internal.AbstractC0910Vq
    public final Object a() {
        return this.c;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.c.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        AbstractC1314dQ abstractC1314dQ = (AbstractC1314dQ) this.c.next();
        this.b = abstractC1314dQ;
        return abstractC1314dQ;
    }

    @Override // java.util.Iterator
    public final void remove() {
        AbstractC1314dQ abstractC1314dQ = this.b;
        if (!(abstractC1314dQ != null)) {
            k2d.a("no calls to next() since the last call to remove()");
        } else {
            this.d.a(abstractC1314dQ.b());
            this.b = null;
        }
    }
}
