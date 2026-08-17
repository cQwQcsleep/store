package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FC extends AbstractC1279d0 {
    public final /* synthetic */ Iterator d;
    public final /* synthetic */ EX e;

    public FC(Iterator it, EX ex) {
        this.d = it;
        this.e = ex;
    }

    @Override // com.android.tools.r8.internal.AbstractC1279d0
    public final Object a() {
        while (this.d.hasNext()) {
            Object next = this.d.next();
            if (this.e.apply(next)) {
                return next;
            }
        }
        this.b = 3;
        return null;
    }
}
