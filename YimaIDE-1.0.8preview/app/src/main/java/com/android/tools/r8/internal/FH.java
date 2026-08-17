package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class FH extends AH {
    public final String a;
    public final String b;

    public FH(String str, String str2) {
        KB.c(str, "enumClassName");
        KB.c(str2, "enumEntryName");
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FH)) {
            return false;
        }
        FH fh = (FH) obj;
        return KB.a((Object) this.a, (Object) fh.a) && KB.a((Object) this.b, (Object) fh.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    @Override // com.android.tools.r8.internal.AH
    public final String toString() {
        return "EnumValue(" + this.a + '.' + this.b + ')';
    }
}
