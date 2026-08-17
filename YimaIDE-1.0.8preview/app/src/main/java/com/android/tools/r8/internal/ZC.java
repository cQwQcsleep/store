package com.android.tools.r8.internal;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ZC extends AbstractC0655Lv {
    public final Set e;
    public final AbstractC0551Hu f;

    public ZC(HashSet hashSet, AbstractC0551Hu abstractC0551Hu) {
        this.e = hashSet;
        this.f = abstractC0551Hu;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.e.contains(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv
    public final Object get(int i) {
        return this.f.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.f.size();
    }
}
