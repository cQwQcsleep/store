package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jA, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1810jA extends W {
    public final InterfaceC1640hA b;

    public C1810jA(InterfaceC1640hA interfaceC1640hA) {
        this.b = interfaceC1640hA;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.b.hasNext();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1640hA
    public final int q() {
        return this.b.q();
    }
}
