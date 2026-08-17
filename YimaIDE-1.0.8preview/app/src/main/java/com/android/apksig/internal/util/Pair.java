package com.android.apksig.internal.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class Pair<A, B> {
    private final A mFirst;
    private final B mSecond;

    private Pair(A a, B b) {
        this.mFirst = a;
        this.mSecond = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || Pair.class != obj.getClass()) {
            return false;
        }
        Pair pair = (Pair) obj;
        A a = this.mFirst;
        A a2 = pair.mFirst;
        if (a == null) {
            if (a2 != null) {
                return false;
            }
        } else if (!a.equals(a2)) {
            return false;
        }
        B b = this.mSecond;
        B b2 = pair.mSecond;
        if (b == null) {
            if (b2 != null) {
                return false;
            }
        } else if (!b.equals(b2)) {
            return false;
        }
        return true;
    }

    public A getFirst() {
        return this.mFirst;
    }

    public B getSecond() {
        return this.mSecond;
    }

    public int hashCode() {
        A a = this.mFirst;
        int iHashCode = ((a == null ? 0 : a.hashCode()) + 31) * 31;
        B b = this.mSecond;
        return iHashCode + (b != null ? b.hashCode() : 0);
    }
}
