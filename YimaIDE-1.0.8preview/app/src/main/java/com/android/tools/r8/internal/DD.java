package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class DD extends ED {
    public final String a;
    public final String b;

    public DD(String str, String str2) {
        KB.c(str2, "desc");
        this.a = str;
        this.b = str2;
    }

    @Override // com.android.tools.r8.internal.ED
    public final String a() {
        return this.a + this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DD)) {
            return false;
        }
        DD dd = (DD) obj;
        return KB.a((Object) this.a, (Object) dd.a) && KB.a((Object) this.b, (Object) dd.b);
    }

    public final int hashCode() {
        return this.b.hashCode() + (this.a.hashCode() * 31);
    }
}
