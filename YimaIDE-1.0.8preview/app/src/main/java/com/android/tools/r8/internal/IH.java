package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IH extends AH {
    public final String a;
    public final int b;

    public IH(int i, String str) {
        KB.c(str, "className");
        this.a = str;
        this.b = i;
        if (i == 0) {
            return;
        }
        w01.a("KClassValue must not have array dimensions. For Array<X>::class, use ArrayKClassValue.");
        throw null;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IH)) {
            return false;
        }
        IH ih = (IH) obj;
        return KB.a((Object) this.a, (Object) ih.a) && this.b == ih.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    @Override // com.android.tools.r8.internal.AH
    public final String toString() {
        return "KClassValue(" + this.a + ')';
    }
}
