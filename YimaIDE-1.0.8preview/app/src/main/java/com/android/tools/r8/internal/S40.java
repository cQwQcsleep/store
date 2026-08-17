package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S40 extends AbstractC0551Hu {
    public final T40 d;

    public S40(T40 t40) {
        this.d = t40;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.d.f[i].getValue();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.d.f.length;
    }
}
