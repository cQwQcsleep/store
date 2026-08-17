package com.android.tools.r8.internal;

import com.android.tools.r8.graph.InterfaceC0339y5;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ju, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1869ju implements Set {
    public final IdentityHashMap b = new IdentityHashMap();
    public final Function c;

    public C1869ju(Function function) {
        this.c = function;
    }

    public static C1869ju a() {
        return new C1869ju(new Function() { // from class: jeh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((InterfaceC0339y5) obj).getContext().getReference();
            }
        });
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean add(Object obj) {
        return this.b.put(this.c.apply(obj), obj) == null;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean addAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= add(it.next());
        }
        return zAdd;
    }

    @Override // java.util.Set, java.util.Collection
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean contains(Object obj) {
        return this.b.containsKey(this.c.apply(obj));
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.b.values().containsAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean isEmpty() {
        return this.b.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.b.values().iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean remove(Object obj) {
        return this.b.remove(this.c.apply(obj)) != null;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean removeAll(Collection collection) {
        Iterator it = collection.iterator();
        boolean zRemove = false;
        while (it.hasNext()) {
            zRemove |= remove(it.next());
        }
        return zRemove;
    }

    @Override // java.util.Set, java.util.Collection
    public final boolean retainAll(Collection collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Object obj : collection) {
            if (this.b.containsKey(this.c.apply(obj))) {
                arrayList.add(obj);
            }
        }
        if (arrayList.size() >= this.b.size()) {
            return false;
        }
        this.b.clear();
        addAll(arrayList);
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public final int size() {
        return this.b.size();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray() {
        return this.b.values().toArray();
    }

    @Override // java.util.Set, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.b.values().toArray(objArr);
    }
}
