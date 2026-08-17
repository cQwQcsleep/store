package com.android.tools.r8.internal;

import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.lb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2010lb0 extends AbstractC1279d0 {
    public final Iterator d;
    public final /* synthetic */ C2095mb0 e;

    public C2010lb0(C2095mb0 c2095mb0) {
        this.e = c2095mb0;
        this.d = c2095mb0.b.iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1279d0
    public final Object a() {
        while (this.d.hasNext()) {
            Object next = this.d.next();
            if (!this.e.c.contains(next)) {
                return next;
            }
        }
        this.b = 3;
        return null;
    }
}
