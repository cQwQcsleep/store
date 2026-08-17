package com.android.tools.r8.internal;

import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class YP extends AbstractC1653hN {
    public final WP d;

    public YP(WP wp) {
        wp.getClass();
        this.d = wp;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.d.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return this.d.containsKey(obj);
    }

    @Override // com.android.tools.r8.internal.AbstractC1653hN
    public final Set e() {
        return new XP(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (this.d.containsKey(obj)) {
            return this.d.get(obj);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return this.d.keySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        if (this.d.containsKey(obj)) {
            return this.d.a(obj);
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.d.keySet().size();
    }
}
