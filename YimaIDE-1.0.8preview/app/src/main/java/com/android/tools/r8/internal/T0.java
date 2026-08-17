package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T0 implements InterfaceC2086mT {
    public final Object b;
    public final int c;

    public T0(int i, Object obj) {
        this.b = obj;
        this.c = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        Object obj2;
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getValue() != null && (entry.getValue() instanceof Integer) && ((obj2 = this.b) != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) && this.c == ((Integer) entry.getValue()).intValue();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2086mT
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
        Object obj = this.b;
        return this.c ^ (obj == null ? 0 : obj.hashCode());
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
