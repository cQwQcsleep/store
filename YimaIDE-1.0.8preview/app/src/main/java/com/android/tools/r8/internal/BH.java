package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class BH extends JH {
    public final boolean a;

    public BH(boolean z) {
        this.a = z;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Boolean.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BH) && this.a == ((BH) obj).a;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.a);
    }
}
