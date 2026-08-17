package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Rj0 implements Comparable {
    public final long b;

    public static int a(long j) {
        return Long.hashCode(j);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j = ((Rj0) obj).b;
        long j2 = this.b ^ Long.MIN_VALUE;
        long j3 = j ^ Long.MIN_VALUE;
        if (j2 < j3) {
            return -1;
        }
        return j2 == j3 ? 0 : 1;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Rj0) && this.b == ((Rj0) obj).b;
    }

    public final int hashCode() {
        return Long.hashCode(this.b);
    }

    public final String toString() {
        long j = this.b;
        if (j >= 0) {
            AbstractC3036xb.a();
            String string = Long.toString(j, 10);
            KB.b(string, "toString(...)");
            return string;
        }
        long j2 = ((j >>> 1) / 10) << 1;
        long j3 = j - (j2 * 10);
        if (j3 >= 10) {
            j3 -= 10;
            j2++;
        }
        AbstractC3036xb.a();
        String string2 = Long.toString(j2, 10);
        KB.b(string2, "toString(...)");
        AbstractC3036xb.a();
        String string3 = Long.toString(j3, 10);
        KB.b(string3, "toString(...)");
        return string2.concat(string3);
    }
}
