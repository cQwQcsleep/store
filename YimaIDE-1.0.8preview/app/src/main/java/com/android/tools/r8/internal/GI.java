package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GI {
    public static final GI c = new GI(null, null);
    public final KI a;
    public final C3100yI b;

    public GI(KI ki, C3100yI c3100yI) {
        this.a = ki;
        this.b = c3100yI;
    }

    public final C3100yI a() {
        return this.b;
    }

    public final KI b() {
        return this.a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GI)) {
            return false;
        }
        GI gi = (GI) obj;
        return this.a == gi.a && KB.a(this.b, gi.b);
    }

    public final int hashCode() {
        KI ki = this.a;
        int iHashCode = (ki == null ? 0 : ki.hashCode()) * 31;
        C3100yI c3100yI = this.b;
        return iHashCode + (c3100yI != null ? c3100yI.hashCode() : 0);
    }

    public final String toString() {
        return "KmTypeProjection(variance=" + this.a + ", type=" + this.b + ')';
    }
}
