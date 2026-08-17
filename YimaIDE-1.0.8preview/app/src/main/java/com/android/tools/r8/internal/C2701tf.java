package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.tf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2701tf extends AbstractC1279d0 {
    public final Iterator d;

    public C2701tf(C2958wf c2958wf) {
        this.d = c2958wf.d.entrySet().iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1279d0
    public final Object a() {
        while (this.d.hasNext()) {
            Map.Entry entry = (Map.Entry) this.d.next();
            int i = ((AtomicInteger) entry.getValue()).get();
            if (i != 0) {
                return new C1485fQ(i, entry.getKey());
            }
        }
        this.b = 3;
        return null;
    }
}
