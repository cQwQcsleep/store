package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class PH extends JH {
    public final long a;

    public PH(long j) {
        this.a = j;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return new Rj0(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof PH) && this.a == ((PH) obj).a;
    }

    public final int hashCode() {
        return Rj0.a(this.a);
    }
}
