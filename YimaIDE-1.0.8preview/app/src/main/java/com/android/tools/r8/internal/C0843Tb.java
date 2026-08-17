package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Tb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0843Tb {
    public final String a;
    public final String b;

    public C0843Tb(String str, String str2) {
        KB.c(str, "className");
        KB.c(str2, "methodName");
        this.a = str;
        this.b = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0843Tb)) {
            return false;
        }
        C0843Tb c0843Tb = (C0843Tb) obj;
        return KB.a((Object) this.a, (Object) c0843Tb.a) && KB.a((Object) this.b, (Object) c0843Tb.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }

    public final String toString() {
        return "ClassAndMethod(className=" + this.a + ", methodName=" + this.b + ")";
    }
}
