package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class W50 implements Comparable {
    public int b;
    public final U50 c;
    public final String d;
    public String e;
    public int f;
    public ArrayList g;
    public HashSet h;

    public W50(String str, U50 u50, String str2, int i) {
        this.e = str;
        this.c = u50;
        this.d = str2;
        this.f = i;
    }

    public final void a(W50 w50) {
        if (w50 != null) {
            ArrayList arrayList = this.g;
            if (arrayList == null) {
                this.g = new ArrayList();
            } else if (arrayList.contains(w50)) {
                return;
            }
            this.g.add(w50);
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        W50 w50 = (W50) obj;
        U50 u50 = this.c;
        U50 u51 = w50.c;
        return u50 != u51 ? u50.compareTo(u51) : this.d.compareTo(w50.d);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && W50.class == obj.getClass()) {
            W50 w50 = (W50) obj;
            if (Objects.equals(this.e, w50.e) && Objects.equals(this.d, w50.d) && this.c == w50.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        U50 u50 = this.c;
        int iHashCode = (u50 != null ? u50.hashCode() : 0) * 31;
        String str = this.d;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        return this.c + ":" + this.d + ":" + this.f;
    }

    public final void a(boolean z) {
        int i = this.b;
        this.b = z ? i | 32 : i & (-33);
    }

    public final boolean a() {
        return (this.b & 32) != 0;
    }
}
