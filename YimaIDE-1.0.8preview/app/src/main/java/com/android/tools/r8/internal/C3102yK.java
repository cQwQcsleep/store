package com.android.tools.r8.internal;

import java.util.AbstractSet;
import java.util.Iterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3102yK extends AbstractSet {
    public final /* synthetic */ BK b;

    public C3102yK(BK bk) {
        this.b = bk;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.b.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C3018xK(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        BK bk = this.b;
        AK akA = bk.a(obj);
        if (akA != null) {
            bk.b(akA, true);
        }
        return akA != null;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.b.e;
    }
}
