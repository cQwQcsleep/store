package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2810uv extends AbstractC0836Su {
    public final /* synthetic */ C2896vv e;

    public C2810uv(C2896vv c2896vv) {
        this.e = c2896vv;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        a().forEach(consumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return a().iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        return new C2725tv(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0836Su
    public final AbstractC0706Nu m() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return a().spliterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return a().iterator();
    }
}
