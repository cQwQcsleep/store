package com.android.tools.r8.internal;

import java.util.AbstractSequentialList;
import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1905kK extends AbstractSequentialList {
    public final /* synthetic */ Object b;
    public final /* synthetic */ C2590sK c;

    public C1905kK(C2590sK c2590sK, Object obj) {
        this.c = c2590sK;
        this.b = obj;
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new C2504rK(this.c, this.b, i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        C2248oK c2248oK = (C2248oK) this.c.h.get(this.b);
        if (c2248oK == null) {
            return 0;
        }
        return c2248oK.c;
    }
}
