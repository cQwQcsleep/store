package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Bu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0395Bu extends AbstractC2554rv {
    public static final /* synthetic */ int f = 0;
    public final transient EnumSet d;
    public transient int e;

    public C0395Bu(EnumSet enumSet) {
        this.d = enumSet;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.d.contains(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        if (collection instanceof C0395Bu) {
            collection = ((C0395Bu) collection).d;
        }
        return this.d.containsAll(collection);
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0395Bu) {
            obj = ((C0395Bu) obj).d;
        }
        return this.d.equals(obj);
    }

    @Override // java.lang.Iterable
    public final void forEach(Consumer consumer) {
        this.d.forEach(consumer);
    }

    @Override // com.android.tools.r8.internal.AbstractC2554rv, java.util.Collection, java.util.Set
    public final int hashCode() {
        int i = this.e;
        if (i != 0) {
            return i;
        }
        int iHashCode = this.d.hashCode();
        this.e = iHashCode;
        return iHashCode;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        Iterator it = this.d.iterator();
        it.getClass();
        return it instanceof Ck0 ? (Ck0) it : new CC(it);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.d.size();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return this.d.spliterator();
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return this.d.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        Iterator it = this.d.iterator();
        it.getClass();
        if (it instanceof Ck0) {
            return (Ck0) it;
        }
        return new CC(it);
    }
}
