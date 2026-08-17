package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class K20 extends AbstractC2392q1 implements J20, Cloneable {
    @Override // com.android.tools.r8.internal.H20
    public final boolean a(Object obj) {
        return false;
    }

    @Override // com.android.tools.r8.internal.AbstractC2392q1, java.util.Map
    public final void clear() {
    }

    public final Object clone() {
        return M20.a;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2294or, java.util.Map
    public final boolean containsKey(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return false;
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return MU.a;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.J20
    public final JU g() {
        return MU.a;
    }

    @Override // java.util.Map
    public final int hashCode() {
        return 0;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return true;
    }

    @Override // com.android.tools.r8.internal.J20, java.util.Map
    public final V30 keySet() {
        return Y30.a;
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

    @Override // java.util.Map
    public final Collection values() {
        return W6.a;
    }

    @Override // java.util.Map
    public final Set keySet() {
        return Y30.a;
    }
}
