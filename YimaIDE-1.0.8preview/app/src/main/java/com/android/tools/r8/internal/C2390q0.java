package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.q0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2390q0 extends AbstractC1653hN {
    public final transient Map d;
    public final /* synthetic */ AbstractC3157z0 e;

    public C2390q0(AbstractC3157z0 abstractC3157z0, Map map) {
        this.e = abstractC3157z0;
        this.d = map;
    }

    public final Map.Entry a(Map.Entry entry) {
        Object key = entry.getKey();
        AbstractC3157z0 abstractC3157z0 = this.e;
        Collection collection = (Collection) entry.getValue();
        K3 k3 = (K3) abstractC3157z0;
        k3.getClass();
        List list = (List) collection;
        return new C3236zu(key, list instanceof RandomAccess ? new C2731u0(k3, key, list, null) : new C3073y0(k3, key, list, null));
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        Map map = this.d;
        AbstractC3157z0 abstractC3157z0 = this.e;
        if (map == abstractC3157z0.f) {
            abstractC3157z0.clear();
            return;
        }
        C2304p0 c2304p0 = new C2304p0(this);
        while (c2304p0.hasNext()) {
            c2304p0.next();
            c2304p0.remove();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map map = this.d;
        map.getClass();
        try {
            return map.containsKey(obj);
        } catch (ClassCastException | NullPointerException unused) {
            return false;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1653hN
    public final Set e() {
        return new C2218o0(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        return this == obj || this.d.equals(obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Collection collection = (Collection) AbstractC1739iN.a(this.d, obj);
        if (collection == null) {
            return null;
        }
        K3 k3 = (K3) this.e;
        k3.getClass();
        List list = (List) collection;
        return list instanceof RandomAccess ? new C2731u0(k3, obj, list, null) : new C3073y0(k3, obj, list, null);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        return this.d.hashCode();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.e.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Collection collection = (Collection) this.d.remove(obj);
        if (collection == null) {
            return null;
        }
        K3 k3 = (K3) this.e;
        k3.getClass();
        ArrayList arrayList = new ArrayList(k3.h);
        arrayList.addAll(collection);
        this.e.g -= collection.size();
        collection.clear();
        return arrayList;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.d.size();
    }

    @Override // java.util.AbstractMap
    public final String toString() {
        return this.d.toString();
    }
}
