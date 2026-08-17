package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2095mb0;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mb0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2095mb0 extends AbstractC2694tb0 {
    public final /* synthetic */ Set b;
    public final /* synthetic */ Set c;

    public C2095mb0(Set set, Set set2) {
        this.b = set;
        this.c = set2;
    }

    public static /* synthetic */ boolean a(Set set, Object obj) {
        return !set.contains(obj);
    }

    public static /* synthetic */ boolean b(Set set, Object obj) {
        return !set.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.b.contains(obj) && !this.c.contains(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.c.containsAll(this.b);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2010lb0(this);
    }

    @Override // java.util.Collection
    public final Stream parallelStream() {
        Stream streamParallelStream = this.b.parallelStream();
        final Set set = this.c;
        return streamParallelStream.filter(new Predicate() { // from class: dmh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2095mb0.a(set, obj);
            }
        });
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        Iterator it = this.b.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!this.c.contains(it.next())) {
                i++;
            }
        }
        return i;
    }

    @Override // java.util.Collection
    public final Stream stream() {
        Stream stream = this.b.stream();
        final Set set = this.c;
        return stream.filter(new Predicate() { // from class: cmh
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C2095mb0.b(set, obj);
            }
        });
    }
}
