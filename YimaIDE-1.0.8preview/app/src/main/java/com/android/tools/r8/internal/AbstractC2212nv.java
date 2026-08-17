package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.nv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2212nv extends AbstractC1955kv {
    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        return a().a(i, objArr);
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        consumer.getClass();
        C1357dv c1357dv = (C1357dv) this;
        int size = c1357dv.e.size();
        for (int i = 0; i < size; i++) {
            consumer.accept(((AbstractC1314dQ) c1357dv.e.get(i)).b());
        }
    }

    public abstract Object get(int i);

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return a().iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        return new C2126mv(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return AbstractC1165be.a(((C1357dv) this).e.size(), 1297, new IntFunction() { // from class: zvh
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.get(i);
            }
        }, (Comparator) null);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return a().iterator();
    }
}
