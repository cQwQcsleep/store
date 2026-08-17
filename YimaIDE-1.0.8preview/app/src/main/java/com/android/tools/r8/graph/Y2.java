package com.android.tools.r8.graph;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Y2 implements Z2 {
    public final InterfaceC0170a3 b;
    public final int c;
    public final int d;
    public final Object e;

    public Y2(InterfaceC0170a3 interfaceC0170a3, Object obj, int i, int i2) {
        this.b = interfaceC0170a3;
        this.c = i;
        this.d = i2;
        this.e = obj;
    }

    public final boolean a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Y2)) {
            return false;
        }
        Y2 y2 = (Y2) obj;
        return this.b.equals(y2.b) && this.c == y2.c && this.d == y2.d;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Y2) {
            return a(obj) && this.e.equals(((Y2) obj).e);
        }
        return false;
    }

    public final int hashCode() {
        return (this.e.hashCode() * 7) + a();
    }

    public final int a() {
        return (this.d * 17) + (this.c * 13) + this.b.hashCode();
    }
}
