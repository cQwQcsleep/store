package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Tj0 implements Comparable {
    public final short b;

    public static int a(short s) {
        return Short.hashCode(s);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return KB.a(this.b & 65535, ((Tj0) obj).b & 65535);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Tj0) && this.b == ((Tj0) obj).b;
    }

    public final int hashCode() {
        return Short.hashCode(this.b);
    }

    public final String toString() {
        return String.valueOf(this.b & 65535);
    }
}
