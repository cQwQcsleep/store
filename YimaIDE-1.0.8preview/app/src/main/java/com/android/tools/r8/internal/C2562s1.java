package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2562s1 implements InterfaceC1713i30 {
    public final Object b;
    public final int c;

    public C2562s1(int i, Object obj) {
        this.b = obj;
        this.c = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getValue() != null && (entry.getValue() instanceof Integer) && this.b == entry.getKey() && this.c == ((Integer) entry.getValue()).intValue();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1713i30
    public final int getIntValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Integer.valueOf(this.c);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.c ^ System.identityHashCode(this.b);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        ((Integer) obj).getClass();
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        return this.b + "->" + this.c;
    }
}
