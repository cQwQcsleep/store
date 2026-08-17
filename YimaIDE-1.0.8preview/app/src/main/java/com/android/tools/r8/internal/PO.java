package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PO extends AbstractC2205no {
    public static final PO a = new PO();

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        C0322w2 c0322w2 = (C0322w2) obj;
        C0322w2 c0322w3 = (C0322w2) obj2;
        return c0322w2.g.equals(c0322w3.g) && c0322w2.i.equals(c0322w3.i);
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        C0322w2 c0322w2 = (C0322w2) obj;
        return c0322w2.i.hashCode() + (c0322w2.g.hashCode() * 31);
    }
}
