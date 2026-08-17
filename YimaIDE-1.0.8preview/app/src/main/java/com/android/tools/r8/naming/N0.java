package com.android.tools.r8.naming;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class N0 {
    public static final /* synthetic */ boolean d = true;
    public final int a;
    public final int b;
    public final boolean c;

    public N0(int i, int i2, boolean z) {
        this.a = i;
        this.b = i2;
        this.c = z;
        if (d || i <= i2) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final int a() {
        if (this.c) {
            return 1;
        }
        return (this.b - this.a) + 1;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof N0)) {
            return false;
        }
        N0 n0 = (N0) obj;
        return this.a == n0.a && this.b == n0.b && this.c == n0.c;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.a), Integer.valueOf(this.b), Boolean.valueOf(this.c));
    }

    public final String toString() {
        boolean z = this.c;
        int i = this.a;
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            return sb.toString();
        }
        return i + ":" + this.b;
    }

    public final boolean a(int i) {
        return this.a <= i && i <= this.b;
    }

    public N0(int i, int i2) {
        this(i, i2, false);
    }
}
