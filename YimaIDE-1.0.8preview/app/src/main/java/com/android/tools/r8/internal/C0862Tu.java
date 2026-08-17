package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Tu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0862Tu extends AbstractC0655Lv {
    public final AbstractC0706Nu e;

    public C0862Tu(AbstractC0706Nu abstractC0706Nu) {
        this.e = abstractC0706Nu;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.e.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv, java.lang.Iterable
    public final void forEach(final Consumer consumer) {
        consumer.getClass();
        this.e.forEach(new BiConsumer() { // from class: oqe
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                consumer.accept(obj);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv
    public final Object get(int i) {
        return ((Map.Entry) this.e.entrySet().a().get(i)).getKey();
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv, com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return this.e.n();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.e.size();
    }

    @Override // com.android.tools.r8.internal.AbstractC0655Lv, com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return this.e.p();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return this.e.n();
    }
}
