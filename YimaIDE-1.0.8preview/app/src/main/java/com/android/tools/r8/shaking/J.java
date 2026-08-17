package com.android.tools.r8.shaking;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J {
    public static final J c = new J(0);
    public static final J d = new J(2);
    public static final J e = new J(4);
    public final J a;
    public final int b;

    public J(int i) {
        this.b = i;
        this.a = a() ? this : new J(i | 1);
    }

    public final boolean a() {
        return (this.b & 1) != 0;
    }

    public final boolean b() {
        return (this.b & 2) != 0;
    }

    public final boolean c() {
        return (this.b & 4) != 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && J.class == obj.getClass() && this.b == ((J) obj).b;
    }

    public final int hashCode() {
        return this.b;
    }
}
