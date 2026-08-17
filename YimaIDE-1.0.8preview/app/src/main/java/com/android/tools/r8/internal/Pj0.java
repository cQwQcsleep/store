package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class Pj0 implements Comparable {
    public final byte b;

    public static int a(byte b) {
        return Byte.hashCode(b);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        return KB.a(this.b & 255, ((Pj0) obj).b & 255);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof Pj0) && this.b == ((Pj0) obj).b;
    }

    public final int hashCode() {
        return Byte.hashCode(this.b);
    }

    public final String toString() {
        return String.valueOf(this.b & 255);
    }
}
