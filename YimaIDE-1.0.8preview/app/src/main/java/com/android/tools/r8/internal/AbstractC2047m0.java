package com.android.tools.r8.internal;

import java.util.ListIterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2047m0 implements ListIterator, InterfaceC1481fM, InterfaceC2942wU {
    public abstract long a();

    public abstract void a(long j);

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        a(((Long) obj).longValue());
    }

    public abstract void b(long j);

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return Long.valueOf(c());
    }

    @Override // java.util.ListIterator, com.android.tools.r8.internal.InterfaceC2942wU
    public final Object previous() {
        return Long.valueOf(a());
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        b(((Long) obj).longValue());
    }
}
