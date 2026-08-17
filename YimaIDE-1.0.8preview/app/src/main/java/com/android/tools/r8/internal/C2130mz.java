package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2130mz extends P implements InterfaceC2045lz, Cloneable {
    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public final boolean a(int i) {
        return false;
    }

    public JU c() {
        return MU.a;
    }

    @Override // com.android.tools.r8.internal.P, java.util.Map
    public final void clear() {
    }

    public final Object clone() {
        return AbstractC2216nz.a;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public JU entrySet() {
        return c();
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1109az
    public Object get(int i) {
        return null;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public int size() {
        return 0;
    }

    public final String toString() {
        return "{}";
    }

    @Override // com.android.tools.r8.internal.InterfaceC2045lz, java.util.Map, com.android.tools.r8.internal.InterfaceC0763Pz, java.util.SortedMap
    public final P30 values() {
        return Y30.a;
    }

    @Override // java.util.Map
    public final Collection values() {
        return Y30.a;
    }

    @Override // java.util.Map, java.util.SortedMap
    public InterfaceC3177zA keySet() {
        return BA.a;
    }
}
