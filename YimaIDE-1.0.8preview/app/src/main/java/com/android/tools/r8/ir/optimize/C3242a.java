package com.android.tools.r8.ir.optimize;

import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.C0705Nt;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.C2283oj0;
import com.android.tools.r8.internal.C2543rl0;
import com.android.tools.r8.internal.W40;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3242a implements Set<C2543rl0> {
    public static final C3242a c;
    public final Set b;

    static {
        int i = AbstractC2554rv.c;
        c = new C3242a(W40.j);
    }

    public C3242a() {
        this.b = AbstractC2780ub0.c();
    }

    public final void a(Predicate predicate, C2543rl0 c2543rl0) {
        for (C2543rl0 c2543rl1 : c2543rl0.a().b) {
            if (c2543rl1.w() && !predicate.test(c2543rl1.i())) {
                this.b.add(c2543rl1);
            }
        }
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        return this.b.add((C2543rl0) obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        return this.b.addAll(collection);
    }

    public final void b(C0333y c0333y, C0705Nt c0705Nt, Consumer consumer) {
        if (this.b.isEmpty()) {
            return;
        }
        C2283oj0 c2283oj0 = new C2283oj0(c0333y, c0705Nt, false);
        consumer.accept(c2283oj0);
        Consumer consumerB = C0822Sg.b();
        c2283oj0.a(this, 4);
        c2283oj0.a(consumerB);
        this.b.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.b.contains(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.b.containsAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.b.iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        return this.b.remove(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.b.removeAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.b.retainAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return this.b.toArray();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.toArray(objArr);
    }

    public C3242a(Set set) {
        this.b = set;
    }

    public final boolean a(C2543rl0 c2543rl0) {
        return this.b.add(c2543rl0);
    }

    public final void a(C0333y c0333y, C0705Nt c0705Nt) {
        a(c0333y, c0705Nt, C0822Sg.b());
    }

    public final void a(C0333y c0333y, C0705Nt c0705Nt, Consumer consumer) {
        if (this.b.isEmpty()) {
            return;
        }
        C2283oj0 c2283oj0 = new C2283oj0(c0333y, c0705Nt, false);
        consumer.accept(c2283oj0);
        c2283oj0.a(this, C0822Sg.b());
        this.b.clear();
    }
}
