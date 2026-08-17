package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Wu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0940Wu extends AbstractC3066xu {
    public final AbstractC0706Nu c;

    public C0940Wu(AbstractC0706Nu abstractC0706Nu) {
        this.c = abstractC0706Nu;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final AbstractC0551Hu a() {
        return new C0914Vu(this, this.c.entrySet().a());
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        if (obj == null) {
            return false;
        }
        Ck0 it = this.c.entrySet().iterator();
        while (it.hasNext()) {
            if (obj.equals(((Map.Entry) it.next()).getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu
    public final boolean e() {
        return true;
    }

    @Override // java.lang.Iterable
    public final void forEach(final Consumer consumer) {
        consumer.getClass();
        this.c.forEach(new BiConsumer() { // from class: jvf
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                consumer.accept(obj2);
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Ck0 iterator() {
        return new C0888Uu(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return this.c.size();
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.Collection, java.lang.Iterable, java.util.List
    public final Spliterator spliterator() {
        return AbstractC1165be.a(this.c.entrySet().spliterator(), new Function() { // from class: ivf
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Map.Entry) obj).getValue();
            }
        });
    }

    @Override // com.android.tools.r8.internal.AbstractC3066xu, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new C0888Uu(this);
    }
}
