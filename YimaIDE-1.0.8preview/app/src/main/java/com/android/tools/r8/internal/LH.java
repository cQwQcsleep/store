package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class LH extends JH {
    public final short a;

    public LH(short s) {
        this.a = s;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Short.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LH) && this.a == ((LH) obj).a;
    }

    public final int hashCode() {
        return Short.hashCode(this.a);
    }
}
