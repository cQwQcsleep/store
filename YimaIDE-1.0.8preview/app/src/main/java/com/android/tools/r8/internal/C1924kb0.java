package com.android.tools.r8.internal;

import defpackage.eih;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1924kb0 extends AbstractC2694tb0 {
    public final /* synthetic */ Set b;
    public final /* synthetic */ Set c;

    public C1924kb0(Set set, Set set2) {
        this.b = set;
        this.c = set2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.b.contains(obj) && this.c.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean containsAll(Collection collection) {
        return this.b.containsAll(collection) && this.c.containsAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return Collections.disjoint(this.c, this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C1839jb0(this);
    }

    @Override // java.util.Collection
    public final Stream parallelStream() {
        Stream streamParallelStream = this.b.parallelStream();
        Set set = this.c;
        Objects.requireNonNull(set);
        return streamParallelStream.filter(new eih(set));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (this.c.contains(it.next())) {
                i++;
            }
        }
        return i;
    }

    @Override // java.util.Collection
    public final Stream stream() {
        Stream stream = this.b.stream();
        Set set = this.c;
        Objects.requireNonNull(set);
        return stream.filter(new eih(set));
    }
}
