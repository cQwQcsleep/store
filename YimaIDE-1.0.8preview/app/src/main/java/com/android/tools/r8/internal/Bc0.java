package com.android.tools.r8.internal;

import java.util.Collections;
import java.util.Spliterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Bc0 extends AbstractC0551Hu {
    public final transient Object d;

    public Bc0(Object obj) {
        obj.getClass();
        this.d = obj;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, java.util.List
    /* JADX INFO: renamed from: e */
    public final AbstractC0551Hu subList(int i, int i2) {
        DX.a(i, i2, 1);
        return i == i2 ? P40.e : this;
    }

    @Override // java.util.List
    public final Object get(int i) {
        DX.a(i, 1);
        return this.d;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return new IC(this.d);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return 1;
    }

    @Override // com.android.tools.r8.internal.AbstractC0551Hu, com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return Collections.singleton(this.d).spliterator();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.d.toString() + ']';
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }
}
