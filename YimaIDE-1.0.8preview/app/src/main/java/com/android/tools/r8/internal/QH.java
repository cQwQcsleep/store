package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QH extends JH {
    public final short a;

    public QH(short s) {
        this.a = s;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return new Tj0(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof QH) && this.a == ((QH) obj).a;
    }

    public final int hashCode() {
        return Tj0.a(this.a);
    }
}
