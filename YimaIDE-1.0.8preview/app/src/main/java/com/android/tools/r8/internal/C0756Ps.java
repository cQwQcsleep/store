package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ps, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0756Ps extends AbstractC1396eN implements R5, Serializable {
    public final /* synthetic */ C0860Ts b;

    public C0756Ps(C0860Ts c0860Ts) {
        this.b = c0860Ts;
    }

    @Override // com.android.tools.r8.internal.R5
    public final Object a(Object obj, Object obj2) {
        return C0860Ts.a(this.b, obj, obj2, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.b.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return this.b.containsValue(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC1396eN
    public final Iterator e() {
        return new C0678Ms(this);
    }

    @Override // com.android.tools.r8.internal.R5
    public final R5 f() {
        return this.b;
    }

    @Override // java.util.Map
    public final void forEach(final BiConsumer biConsumer) {
        biConsumer.getClass();
        this.b.forEach(new BiConsumer() { // from class: mhb
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                biConsumer.accept(obj2, obj);
            }
        });
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        return AbstractC1739iN.a(this.b.b(AbstractC1189bt.a(obj), obj));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new C0730Os(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        return C0860Ts.a(this.b, obj, obj2, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        C0627Ks c0627KsB = this.b.b(AbstractC1189bt.a(obj), obj);
        if (c0627KsB == null) {
            return null;
        }
        this.b.a(c0627KsB);
        c0627KsB.i = null;
        c0627KsB.h = null;
        return c0627KsB.b;
    }

    @Override // java.util.Map
    public final void replaceAll(BiFunction biFunction) {
        biFunction.getClass();
        C0860Ts c0860Ts = this.b;
        c0860Ts.clear();
        for (C0627Ks c0627Ks = c0860Ts.d; c0627Ks != null; c0627Ks = c0627Ks.h) {
            Object obj = c0627Ks.c;
            C0860Ts.a(this.b, obj, biFunction.apply(obj, c0627Ks.b), false);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1396eN, java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.b.f;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return this.b.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map, com.android.tools.r8.internal.R5
    public final Set values() {
        return this.b.keySet();
    }
}
