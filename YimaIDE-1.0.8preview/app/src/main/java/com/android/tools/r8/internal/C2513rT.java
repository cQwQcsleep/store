package com.android.tools.r8.internal;

import java.util.Map;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rT, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2513rT implements InterfaceC2086mT, Map.Entry {
    public int b;
    public final /* synthetic */ C2855vT c;

    public C2513rT(C2855vT c2855vT, int i) {
        this.c = c2855vT;
        this.b = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object obj2 = this.c.c[this.b];
        if (obj2 != null ? obj2.equals(entry.getKey()) : entry.getKey() == null) {
            if (this.c.d[this.b] == ((Integer) entry.getValue()).intValue()) {
                return true;
            }
        }
        return false;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2086mT
    public final int getIntValue() {
        return this.c.d[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c.c[this.b];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        return Integer.valueOf(this.c.d[this.b]);
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object obj = this.c.c[this.b];
        return this.c.d[this.b] ^ (obj == null ? 0 : obj.hashCode());
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        int iIntValue = ((Integer) obj).intValue();
        int[] iArr = this.c.d;
        int i = this.b;
        int i2 = iArr[i];
        iArr[i] = iIntValue;
        return Integer.valueOf(i2);
    }

    public final String toString() {
        return this.c.c[this.b] + "=>" + this.c.d[this.b];
    }
}
