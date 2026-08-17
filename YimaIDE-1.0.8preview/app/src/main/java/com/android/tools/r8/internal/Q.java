package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Q implements InterfaceC1959kz {
    public int b;
    public Object c;

    public Q(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1959kz
    public final int a() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getKey() != null && (entry.getKey() instanceof Integer) && this.b == ((Integer) entry.getKey()).intValue() && this.c == entry.getValue();
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return Integer.valueOf(this.b);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public int hashCode() {
        int i = this.b;
        Object obj = this.c;
        return (obj == null ? 0 : System.identityHashCode(obj)) ^ i;
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return this.b + "->" + this.c;
    }
}
