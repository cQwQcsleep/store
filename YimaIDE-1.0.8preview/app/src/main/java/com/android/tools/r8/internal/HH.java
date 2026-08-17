package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class HH extends JH {
    public final int a;

    public HH(int i) {
        this.a = i;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Integer.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HH) && this.a == ((HH) obj).a;
    }

    public final int hashCode() {
        return Integer.hashCode(this.a);
    }
}
