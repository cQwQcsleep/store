package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2476r1 implements I20 {
    public final Object b;
    public final boolean c;

    public C2476r1(Object obj, boolean z) {
        this.b = obj;
        this.c = z;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getValue() != null && (entry.getValue() instanceof Boolean) && this.b == entry.getKey() && this.c == ((Boolean) entry.getValue()).booleanValue();
    }

    @Override // com.android.tools.r8.internal.I20
    public final boolean getBooleanValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Boolean.valueOf(this.c);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return (this.c ? 1231 : 1237) ^ System.identityHashCode(this.b);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        ((Boolean) obj).getClass();
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        return this.b + "->" + this.c;
    }
}
