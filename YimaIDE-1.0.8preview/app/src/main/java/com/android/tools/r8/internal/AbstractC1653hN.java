package com.android.tools.r8.internal;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hN, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1653hN extends AbstractMap {
    public transient Set b;
    public transient C1567gN c;

    public abstract Set e();

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.b;
        if (set != null) {
            return set;
        }
        Set setE = e();
        this.b = setE;
        return setE;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        C1567gN c1567gN = this.c;
        if (c1567gN != null) {
            return c1567gN;
        }
        C1567gN c1567gN2 = new C1567gN(this);
        this.c = c1567gN2;
        return c1567gN2;
    }
}
