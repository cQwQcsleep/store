package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.o0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2218o0 extends AbstractC1228cN {
    public final /* synthetic */ C2390q0 b;

    public C2218o0(C2390q0 c2390q0) {
        this.b = c2390q0;
    }

    @Override // com.android.tools.r8.internal.AbstractC1228cN
    public final Map a() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.AbstractC1228cN, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        Set setEntrySet = this.b.d.entrySet();
        setEntrySet.getClass();
        try {
            return setEntrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public final Iterator iterator() {
        return new C2304p0(this.b);
    }

    @Override // com.android.tools.r8.internal.AbstractC1228cN, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean remove(Object obj) {
        boolean zContains;
        Object objRemove;
        Set setEntrySet = this.b.d.entrySet();
        setEntrySet.getClass();
        try {
            zContains = setEntrySet.contains(obj);
        } catch (ClassCastException | NullPointerException unused) {
            zContains = false;
        }
        if (!zContains) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Objects.requireNonNull(entry);
        AbstractC3157z0 abstractC3157z0 = this.b.e;
        Object key = entry.getKey();
        Map map = abstractC3157z0.f;
        map.getClass();
        try {
            objRemove = map.remove(key);
        } catch (ClassCastException | NullPointerException unused2) {
            objRemove = null;
        }
        Collection collection = (Collection) objRemove;
        if (collection == null) {
            return true;
        }
        int size = collection.size();
        collection.clear();
        abstractC3157z0.g -= size;
        return true;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.Set
    public final Spliterator spliterator() {
        Spliterator spliterator = this.b.d.entrySet().spliterator();
        final C2390q0 c2390q0 = this.b;
        return AbstractC1165be.a(spliterator, new Function() { // from class: zwh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return c2390q0.a((Map.Entry) obj);
            }
        });
    }
}
