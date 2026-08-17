package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.kI, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1903kI {
    public final C3100yI a;
    public final String b;

    public C1903kI(C3100yI c3100yI, String str) {
        KB.c(c3100yI, "type");
        this.a = c3100yI;
        this.b = str;
    }

    public final C3100yI a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1903kI)) {
            return false;
        }
        C1903kI c1903kI = (C1903kI) obj;
        return KB.a(this.a, c1903kI.a) && KB.a((Object) this.b, (Object) c1903kI.b);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        String str = this.b;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return "KmFlexibleTypeUpperBound(type=" + this.a + ", typeFlexibilityId=" + this.b + ')';
    }
}
