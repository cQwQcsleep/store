package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class CH extends JH {
    public final byte a;

    public CH(byte b) {
        this.a = b;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return Byte.valueOf(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof CH) && this.a == ((CH) obj).a;
    }

    public final int hashCode() {
        return Byte.hashCode(this.a);
    }
}
