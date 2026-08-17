package com.android.tools.r8.graph;

/* JADX INFO: renamed from: com.android.tools.r8.graph.d3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0191d3 {
    public static final /* synthetic */ boolean c = true;
    public final I2 a;
    public final C0322w2 b;

    public C0191d3(I2 i2) {
        this.a = i2;
    }

    public final I2 a() {
        return this.a;
    }

    public C0322w2 b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0191d3)) {
            return false;
        }
        C0191d3 c0191d3 = (C0191d3) obj;
        return this.a == c0191d3.a && this.b == c0191d3.b;
    }

    public final int hashCode() {
        if (!c) {
            if ((this.a == null) == (this.b == null)) {
                x1f.a();
                return 0;
            }
        }
        return System.identityHashCode(this.b) + System.identityHashCode(this.a);
    }

    public final String toString() {
        I2 i2 = this.a;
        String strZ0 = i2 == null ? "null" : i2.Z0();
        C0322w2 c0322w2 = this.b;
        return "[enclosingClass: " + strZ0 + ", enclosingMethod: " + (c0322w2 != null ? c0322w2.m0() : "null") + "]";
    }

    public C0191d3(C0322w2 c0322w2) {
        this.b = c0322w2;
    }
}
