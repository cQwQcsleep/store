package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F50 {
    public final U50 a;
    public final String b;
    public final String c;

    public F50(U50 u50, String str, String str2) {
        KB.c(u50, "type");
        KB.c(str, TypeBlock.NAME_name);
        this.a = u50;
        this.b = str;
        this.c = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof F50)) {
            return false;
        }
        F50 f50 = (F50) obj;
        return this.a == f50.a && KB.a((Object) this.b, (Object) f50.b) && KB.a((Object) this.c, (Object) f50.c);
    }

    public final int hashCode() {
        int iHashCode = (this.b.hashCode() + (this.a.hashCode() * 31)) * 31;
        String str = this.c;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return "ResourceId(type=" + this.a + ", name=" + this.b + ", packageName=" + this.c + ")";
    }
}
