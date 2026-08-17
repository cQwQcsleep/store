package com.android.tools.r8.internal;

import java.util.IdentityHashMap;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.c6, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1207c6 extends AbstractC1123b6 implements W5 {
    public static final /* synthetic */ boolean e = true;
    public final IdentityHashMap d = new IdentityHashMap();

    @Override // com.android.tools.r8.internal.W5
    public final Object c(Object obj) {
        Set setE = e(obj);
        if (setE.isEmpty()) {
            return null;
        }
        return setE.size() == 1 ? setE.iterator().next() : this.d.get(obj);
    }

    @Override // com.android.tools.r8.internal.W5
    public final Object d(Object obj) {
        return this.c.get(obj);
    }
}
