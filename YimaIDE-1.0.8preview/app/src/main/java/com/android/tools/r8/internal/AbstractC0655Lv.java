package com.android.tools.r8.internal;

import java.util.Comparator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Lv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0655Lv extends AbstractC1955kv {
    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final int a(int i, Object[] objArr) {
        return a().a(i, objArr);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer consumer) {
        consumer.getClass();
        int size = size();
        for (int i = 0; i < size; i++) {
            consumer.accept(get(i));
        }
    }

    public abstract Object get(int i);

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Ck0 iterator() {
        return a().iterator();
    }

    @Override // com.android.tools.r8.internal.AbstractC1955kv
    public final AbstractC0551Hu l() {
        return new C0630Kv(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public Spliterator spliterator() {
        return AbstractC1165be.a(size(), 1297, new IntFunction() { // from class: tj9
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.get(i);
            }
        }, (Comparator) null);
    }
}
