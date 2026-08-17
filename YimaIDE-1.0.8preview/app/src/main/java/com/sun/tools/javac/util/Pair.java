package com.sun.tools.javac.util;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Pair<A, B> {
    public final A fst;
    public final B snd;

    public Pair(A a, B b) {
        this.fst = a;
        this.snd = b;
    }

    public static <A, B> Pair<A, B> of(A a, B b) {
        return new Pair<>(a, b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return Objects.equals(this.fst, pair.fst) && Objects.equals(this.snd, pair.snd);
    }

    public int hashCode() {
        A a = this.fst;
        B b = this.snd;
        if (a != null) {
            return b == null ? a.hashCode() + 2 : (a.hashCode() * 17) + this.snd.hashCode();
        }
        if (b == null) {
            return 0;
        }
        return b.hashCode() + 1;
    }

    public String toString() {
        return "Pair[" + this.fst + "," + this.snd + "]";
    }
}
