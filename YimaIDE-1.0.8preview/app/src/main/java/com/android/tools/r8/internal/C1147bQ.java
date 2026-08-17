package com.android.tools.r8.internal;

import defpackage.lka;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.bQ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1147bQ extends R0 {
    public final WP d;

    public C1147bQ(WP wp) {
        this.d = wp;
    }

    @Override // com.android.tools.r8.internal.R0, com.android.tools.r8.internal.InterfaceC1231cQ
    public final Set F() {
        return this.d.keySet();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(int i, Object obj) {
        AbstractC0871Ud.a(i, "occurrences");
        if (i == 0) {
            return b(obj);
        }
        Collection collection = (Collection) AbstractC1739iN.a(this.d.b(), obj);
        if (collection == null) {
            return 0;
        }
        int size = collection.size();
        if (i >= size) {
            collection.clear();
            return size;
        }
        Iterator it = collection.iterator();
        for (int i2 = 0; i2 < i; i2++) {
            it.next();
            it.remove();
        }
        return size;
    }

    @Override // com.android.tools.r8.internal.R0
    public final int c() {
        return this.d.b().size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.d.clear();
    }

    @Override // com.android.tools.r8.internal.R0, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.d.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.R0
    public final Iterator d() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.R0
    public final Iterator e() {
        return new C1061aQ(this.d.b().entrySet().iterator());
    }

    @Override // java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1231cQ
    public final void forEach(final Consumer consumer) {
        consumer.getClass();
        this.d.a().forEach(new Consumer() { // from class: rhg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                consumer.accept(((Map.Entry) obj).getKey());
            }
        });
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new UM(this.d.a().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.d.size();
    }

    @Override // java.util.Collection, java.lang.Iterable, com.android.tools.r8.internal.InterfaceC1231cQ
    public final Spliterator spliterator() {
        return AbstractC1165be.a(this.d.a().spliterator(), new lka());
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(Object obj) {
        Collection collection = (Collection) AbstractC1739iN.a(this.d.b(), obj);
        if (collection == null) {
            return 0;
        }
        return collection.size();
    }
}
