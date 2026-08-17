package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class KH extends JH {
    public final long a;

    public KH(long j) {
        this.a = j;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Long.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof KH) && this.a == ((KH) obj).a;
    }

    public final int hashCode() {
        return Long.hashCode(this.a);
    }
}
