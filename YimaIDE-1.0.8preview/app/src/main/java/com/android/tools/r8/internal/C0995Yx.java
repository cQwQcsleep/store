package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Yx, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0995Yx extends K implements InterfaceC0969Xx, Cloneable {
    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public final boolean a(int i) {
        return false;
    }

    public JU b() {
        return MU.a;
    }

    @Override // com.android.tools.r8.internal.K, java.util.Map
    public final void clear() {
    }

    public final Object clone() {
        return AbstractC1021Zx.a;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map, java.util.SortedMap
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public JU entrySet() {
        return b();
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0917Vx
    public final Object get(int i) {
        return null;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return 0;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return true;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final int size() {
        return 0;
    }

    public final String toString() {
        return "{}";
    }

    @Override // com.android.tools.r8.internal.InterfaceC0969Xx, java.util.Map, com.android.tools.r8.internal.InterfaceC0425Cy, java.util.SortedMap
    public final InterfaceC3028xU values() {
        return MU.a;
    }

    @Override // java.util.Map
    public final Collection values() {
        return MU.a;
    }

    @Override // java.util.Map, java.util.SortedMap
    public InterfaceC3177zA keySet() {
        return BA.a;
    }
}
