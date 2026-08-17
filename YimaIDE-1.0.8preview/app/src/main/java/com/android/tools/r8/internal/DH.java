package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DH extends JH {
    public final char a;

    public DH(char c) {
        this.a = c;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Character.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DH) && this.a == ((DH) obj).a;
    }

    public final int hashCode() {
        return Character.hashCode(this.a);
    }
}
