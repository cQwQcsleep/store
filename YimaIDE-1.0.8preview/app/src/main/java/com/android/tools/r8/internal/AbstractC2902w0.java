package com.android.tools.r8.internal;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Spliterator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.w0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2902w0 extends AbstractCollection {
    public final Object b;
    public Collection c;
    public final AbstractC2902w0 d;
    public final Collection e;
    public final /* synthetic */ AbstractC3157z0 f;

    public AbstractC2902w0(AbstractC3157z0 abstractC3157z0, Object obj, Collection collection, AbstractC2902w0 abstractC2902w0) {
        this.f = abstractC3157z0;
        this.b = obj;
        this.c = collection;
        this.d = abstractC2902w0;
        this.e = abstractC2902w0 == null ? null : abstractC2902w0.c;
    }

    public final void a() {
        AbstractC2902w0 abstractC2902w0 = this.d;
        if (abstractC2902w0 != null) {
            abstractC2902w0.a();
        } else {
            this.f.f.put(this.b, this.c);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        b();
        boolean zIsEmpty = this.c.isEmpty();
        boolean zAdd = this.c.add(obj);
        if (zAdd) {
            this.f.g++;
            if (zIsEmpty) {
                a();
            }
        }
        return zAdd;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        b();
        int size = this.c.size();
        boolean zAddAll = this.c.addAll(collection);
        if (zAddAll) {
            int size2 = this.c.size();
            this.f.g += size2 - size;
            if (size == 0) {
                a();
            }
        }
        return zAddAll;
    }

    public final void b() {
        Collection collection;
        AbstractC2902w0 abstractC2902w0 = this.d;
        if (abstractC2902w0 != null) {
            abstractC2902w0.b();
            if (this.d.c == this.e) {
                return;
            }
            a1e.a();
            return;
        }
        if (!this.c.isEmpty() || (collection = (Collection) this.f.f.get(this.b)) == null) {
            return;
        }
        this.c = collection;
    }

    public final void c() {
        AbstractC2902w0 abstractC2902w0 = this.d;
        if (abstractC2902w0 != null) {
            abstractC2902w0.c();
        } else if (this.c.isEmpty()) {
            this.f.f.remove(this.b);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        b();
        int size = this.c.size();
        if (size == 0) {
            return;
        }
        this.c.clear();
        this.f.g -= size;
        c();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        b();
        return this.c.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean containsAll(Collection collection) {
        b();
        return this.c.containsAll(collection);
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        b();
        return this.c.equals(obj);
    }

    @Override // java.util.Collection
    public final int hashCode() {
        b();
        return this.c.hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        b();
        return new C2816v0(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        b();
        boolean zRemove = this.c.remove(obj);
        if (zRemove) {
            this.f.g--;
            c();
        }
        return zRemove;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        b();
        int size = this.c.size();
        boolean zRemoveAll = this.c.removeAll(collection);
        if (zRemoveAll) {
            int size2 = this.c.size();
            this.f.g += size2 - size;
            c();
        }
        return zRemoveAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        b();
        int size = this.c.size();
        boolean zRetainAll = this.c.retainAll(collection);
        if (zRetainAll) {
            int size2 = this.c.size();
            this.f.g += size2 - size;
            c();
        }
        return zRetainAll;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        b();
        return this.c.size();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Spliterator spliterator() {
        b();
        return this.c.spliterator();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        b();
        return this.c.toString();
    }
}
