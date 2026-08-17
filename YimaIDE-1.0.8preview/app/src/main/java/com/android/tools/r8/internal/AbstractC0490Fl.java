package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.H0;
import defpackage.eh5;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Fl, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0490Fl<T extends com.android.tools.r8.graph.H0> implements Collection<T> {
    public static final /* synthetic */ boolean c = true;
    public Map b;

    public AbstractC0490Fl() {
        this.b = a();
    }

    public abstract Map a();

    public final Set a(IntFunction intFunction) {
        final Set set = (Set) intFunction.apply(this.b.size());
        forEach(new Consumer() { // from class: gh5
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                set.add(((H0) obj).e());
            }
        });
        return set;
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add((com.android.tools.r8.graph.H0) it.next());
        }
        return zAdd;
    }

    public Set<C0231j1> b() {
        if (c || (this.b instanceof IdentityHashMap)) {
            return a(new eh5());
        }
        x1f.a();
        return null;
    }

    @Override // java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (!(obj instanceof com.android.tools.r8.graph.H0)) {
            return false;
        }
        return this.b.containsKey(((com.android.tools.r8.graph.H0) obj).getReference());
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return AbstractC3179zC.a(collection, new EX() { // from class: fh5
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return this.b.contains(obj);
            }
        });
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.b.values().iterator();
    }

    public abstract Map j(int i);

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        if (obj instanceof com.android.tools.r8.graph.H0) {
            if (((com.android.tools.r8.graph.H0) this.b.remove(((com.android.tools.r8.graph.H0) obj).getReference())) != null) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Collection
    public boolean removeIf(Predicate<? super T> predicate) {
        return this.b.values().removeIf(predicate);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.b.values().retainAll(collection);
    }

    @Override // java.util.Collection
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Collection
    public Stream<T> stream() {
        return this.b.values().stream();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return this.b.values().toArray();
    }

    public AbstractC0490Fl(int i) {
        this.b = j(i);
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.values().toArray(objArr);
    }

    @Override // java.util.Collection
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public boolean add(T t) {
        return ((com.android.tools.r8.graph.H0) this.b.put((C0322w2) t.getReference(), t)) == null;
    }

    public boolean a(C0231j1 c0231j1) {
        return ((com.android.tools.r8.graph.H0) this.b.remove(c0231j1.getReference())) != null;
    }

    public final boolean b(com.android.tools.r8.graph.H0 h0) {
        return this.b.containsKey(h0.getReference());
    }
}
