package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class NH extends JH {
    public final byte a;

    public NH(byte b) {
        this.a = b;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return new Pj0(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof NH) && this.a == ((NH) obj).a;
    }

    public final int hashCode() {
        return Pj0.a(this.a);
    }
}
