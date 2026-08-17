package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ql, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0775Ql implements Collection {
    public static final C0775Ql c = new C0775Ql(Collections.EMPTY_SET);
    public final Set b;

    public C0775Ql(Set set) {
        this.b = set;
    }

    public static C0775Ql b() {
        return new C0775Ql(new LinkedHashSet());
    }

    public final void a(Iterable iterable, Function function) {
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            AbstractC3179zC.a((Iterable) function.apply(it.next()), (Collection) this);
        }
    }

    @Override // java.util.Collection
    public final boolean add(Object obj) {
        return this.b.add((com.android.tools.r8.graph.B2) obj);
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        return this.b.addAll(collection);
    }

    public final boolean c(C0231j1 c0231j1) {
        return this.b.remove(c0231j1.g1());
    }

    @Override // java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return this.b.contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.b.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.b.iterator();
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        return this.b.remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.b.removeAll(collection);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.b.retainAll(collection);
    }

    @Override // java.util.Collection
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return this.b.toArray();
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.toArray(objArr);
    }

    public final boolean b(C0231j1 c0231j1) {
        return this.b.contains(c0231j1.g1());
    }

    public final void b(Iterable iterable) {
        iterable.forEach(new Consumer() { // from class: y0c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.c((C0231j1) obj);
            }
        });
    }

    public static C0775Ql a() {
        return new C0775Ql(new HashSet());
    }

    public final boolean a(C0231j1 c0231j1) {
        return a(c0231j1.getReference());
    }

    public final boolean a(com.android.tools.r8.graph.H0 h0) {
        return a(h0.getReference());
    }

    public final void a(Iterable iterable) {
        iterable.forEach(new Consumer() { // from class: z0c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((C0231j1) obj);
            }
        });
    }

    public final void a(C0775Ql c0775Ql) {
        this.b.addAll(c0775Ql.b);
    }

    public final boolean a(C0322w2 c0322w2) {
        return this.b.add(AbstractC0507Gc.a(c0322w2, c0322w2));
    }
}
