package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EH extends JH {
    public final double a;

    public EH(double d) {
        this.a = d;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Double.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof EH) && Double.compare(this.a, ((EH) obj).a) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this.a);
    }
}
