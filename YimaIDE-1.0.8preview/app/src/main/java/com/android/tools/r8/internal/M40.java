package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class M40 extends AbstractC0836Su {
    public final /* synthetic */ N40 e;

    public M40(N40 n40) {
        this.e = n40;
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        a().forEach(consumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC0836Su, com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.e.f.j;
    }

    @Override // com.android.tools.r8.internal.AbstractC0836Su, com.android.tools.r8.internal.AbstractC2554rv
    public final boolean i() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return a().iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        return new L40(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0836Su
    public final AbstractC0706Nu m() {
        return this.e;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return a().iterator();
    }
}
