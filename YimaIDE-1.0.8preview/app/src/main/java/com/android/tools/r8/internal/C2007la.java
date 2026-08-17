package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;

/* JADX INFO: renamed from: com.android.tools.r8.internal.la, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2007la extends AbstractC2092ma {
    public final List a;

    public C2007la(ArrayList arrayList) {
        this.a = arrayList;
    }

    @Override // com.android.tools.r8.internal.AbstractC2092ma
    public final void a(Collection collection, AbstractC2350pa abstractC2350pa, ExecutorService executorService) {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ((InterfaceC1836ja) it.next()).a(collection, abstractC2350pa, executorService);
        }
    }
}
