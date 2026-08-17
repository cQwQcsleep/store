package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.zH, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3184zH extends AH {
    public final String a;
    public final int b;
    public final String c;

    public C3184zH(int i, String str) {
        KB.c(str, "className");
        this.a = str;
        this.b = i;
        if (i <= 0) {
            w01.a("ArrayKClassValue must have at least one dimension. For regular X::class argument, use KClassValue.");
            throw null;
        }
        StringBuilder sb = new StringBuilder("ArrayKClassValue(");
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("kotlin/Array<");
        }
        sb.append(this.a);
        int i3 = this.b;
        for (int i4 = 0; i4 < i3; i4++) {
            sb.append(">");
        }
        sb.append(")");
        String string = sb.toString();
        KB.b(string, "toString(...)");
        this.c = string;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3184zH)) {
            return false;
        }
        C3184zH c3184zH = (C3184zH) obj;
        return KB.a((Object) this.a, (Object) c3184zH.a) && this.b == c3184zH.b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b) + (this.a.hashCode() * 31);
    }

    @Override // com.android.tools.r8.internal.AH
    public final String toString() {
        return this.c;
    }
}
