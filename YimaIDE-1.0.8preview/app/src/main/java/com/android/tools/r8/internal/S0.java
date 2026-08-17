package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class S0 implements Map.Entry {
    public final Object b;
    public final boolean c;

    public S0(Object obj, boolean z) {
        this.b = obj;
        this.c = z;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getValue() != null && (entry.getValue() instanceof Boolean) && ((obj2 = this.b) != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) && this.c == ((Boolean) entry.getValue()).booleanValue();
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
        Object obj = this.b;
        return (this.c ? 1231 : 1237) ^ (obj == null ? 0 : obj.hashCode());
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
