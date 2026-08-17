package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Pg0 implements Comparable {
    public final Object b;

    public Pg0(C1856jk c1856jk, Object obj) {
        this.b = obj;
        EnumC1686hk enumC1686hk = ((C1856jk) Collections.unmodifiableList(Arrays.asList(c1856jk.i().g)).get(0)).h.b;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        Sg0.a.info("Invalid key for map field.");
        return -1;
    }
}
