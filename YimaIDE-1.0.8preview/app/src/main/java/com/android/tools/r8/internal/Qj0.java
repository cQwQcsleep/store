package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Qj0 implements Comparable {
    public final int b;

    public static int b(int i) {
        return Integer.hashCode(i);
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return KB.a(this.b ^ Integer.MIN_VALUE, ((Qj0) obj).b ^ Integer.MIN_VALUE);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Qj0) && this.b == ((Qj0) obj).b;
    }

    public final int hashCode() {
        return Integer.hashCode(this.b);
    }

    public final String toString() {
        return String.valueOf(((long) this.b) & 4294967295L);
    }
}
