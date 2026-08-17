package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class MH extends JH {
    public final String a;

    public MH(String str) {
        this.a = str;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof MH) && KB.a((Object) this.a, (Object) ((MH) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
