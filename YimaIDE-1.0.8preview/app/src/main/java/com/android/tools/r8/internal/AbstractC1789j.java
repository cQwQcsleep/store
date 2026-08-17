package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1789j extends AbstractC1705i implements InterfaceC2942wU, ListIterator {
    public abstract boolean a();

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        ((Boolean) obj).getClass();
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return Boolean.valueOf(a());
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        ((Boolean) obj).getClass();
        throw new UnsupportedOperationException();
    }
}
