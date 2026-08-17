package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class I implements InterfaceC2300ox {
    public final int b;
    public final int c;

    public I(int i, int i2) {
        this.b = i;
        this.c = i2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2300ox
    public final int a() {
        return this.b;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        return entry.getKey() != null && (entry.getKey() instanceof Integer) && entry.getValue() != null && (entry.getValue() instanceof Integer) && this.b == ((Integer) entry.getKey()).intValue() && this.c == ((Integer) entry.getValue()).intValue();
    }

    @Override // com.android.tools.r8.internal.InterfaceC2300ox
    public final int getIntValue() {
        return this.c;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return Integer.valueOf(this.b);
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Integer.valueOf(this.c);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        return this.c ^ this.b;
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
