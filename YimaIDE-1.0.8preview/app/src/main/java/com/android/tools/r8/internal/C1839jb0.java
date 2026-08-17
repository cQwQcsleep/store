package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1839jb0 extends AbstractC1279d0 {
    public final Iterator d;
    public final /* synthetic */ C1924kb0 e;

    public C1839jb0(C1924kb0 c1924kb0) {
        this.e = c1924kb0;
        this.d = c1924kb0.b.iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1279d0
    public final Object a() {
        while (this.d.hasNext()) {
            Object next = this.d.next();
            if (this.e.c.contains(next)) {
                return next;
            }
        }
        this.b = 3;
        return null;
    }
}
