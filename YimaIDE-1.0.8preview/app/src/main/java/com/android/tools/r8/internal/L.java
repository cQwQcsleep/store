package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class L implements InterfaceC0943Wx {
    public int b;
    public Object c;

    public L(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0943Wx
    public final int a() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry.getKey() != null && (entry.getKey() instanceof Integer) && this.b == ((Integer) entry.getKey()).intValue()) {
            Object obj2 = this.c;
            if (obj2 == null) {
                if (entry.getValue() == null) {
                    return true;
                }
            } else if (obj2.equals(entry.getValue())) {
                return true;
            }
        }
        return false;
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
        return (obj == null ? 0 : obj.hashCode()) ^ i;
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return this.b + "->" + this.c;
    }
}
