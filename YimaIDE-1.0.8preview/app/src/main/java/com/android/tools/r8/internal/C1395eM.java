package com.android.tools.r8.internal;

/* JADX INFO: renamed from: com.android.tools.r8.internal.eM, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1395eM {
    public static final /* synthetic */ boolean c = true;
    public final long a;
    public final long b;

    public C1395eM(int i, int i2) {
        if (!c && i > i2) {
            x1f.a();
            throw null;
        }
        this.a = i;
        this.b = i2;
    }

    public final boolean a(long j) {
        return this.a <= j && j <= this.b;
    }

    public long b() {
        return this.a;
    }

    public final long c() {
        if (c || d()) {
            return this.a;
        }
        x1f.a();
        return 0L;
    }

    public final boolean d() {
        return this.a == this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C1395eM) {
            C1395eM c1395eM = (C1395eM) obj;
            if (c1395eM.a == this.a && c1395eM.b == this.b) {
                return true;
            }
        }
        return false;
    }

    public final String toString() {
        return "[" + this.a + ", " + this.b + "]";
    }

    public long a() {
        return this.b;
    }

    public C1395eM(long j, long j2) {
        if (!c && j > j2) {
            x1f.a();
            throw null;
        }
        this.a = j;
        this.b = j2;
    }
}
