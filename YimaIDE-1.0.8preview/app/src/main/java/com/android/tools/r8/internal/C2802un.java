package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.un, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2802un {
    public final int a;
    public final String b;
    public final String c;
    public final E60 d;

    public C2802un(int i, String str, String str2, E60 e60) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = e60;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C2802un)) {
            return false;
        }
        C2802un c2802un = (C2802un) obj;
        return this.a == c2802un.a && KB.a((Object) this.b, (Object) c2802un.b) && KB.a((Object) this.c, (Object) c2802un.c) && KB.a(this.d, c2802un.d);
    }

    public final int hashCode() {
        return this.d.hashCode() + ((this.c.hashCode() + ((this.b.hashCode() + (Integer.hashCode(this.a) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "EntryWrapper(id=" + this.a + ", packageName=" + this.b + ", type=" + this.c + ", entry=" + this.d + ")";
    }
}
