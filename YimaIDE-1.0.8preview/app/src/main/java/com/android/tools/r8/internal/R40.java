package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R40 extends AbstractC0655Lv {
    public final T40 e;

    public R40(T40 t40) {
        this.e = t40;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.e.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv
    public final Object get(int i) {
        return this.e.f[i].getKey();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.e.f.length;
    }
}
