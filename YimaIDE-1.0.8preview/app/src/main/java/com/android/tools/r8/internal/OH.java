package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class OH extends JH {
    public final int a;

    public OH(int i) {
        this.a = i;
    }

    @Override // com.android.tools.r8.internal.JH
    public final Object a() {
        return new Qj0(this.a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof OH) && this.a == ((OH) obj).a;
    }

    public final int hashCode() {
        return Qj0.b(this.a);
    }
}
