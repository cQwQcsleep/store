package org.antlr.v4.runtime.misc;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class Pair<A, B> implements Serializable {
    public final A a;
    public final B b;

    public Pair(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        ObjectEqualityComparator objectEqualityComparator = ObjectEqualityComparator.INSTANCE;
        return objectEqualityComparator.equals(this.a, pair.a) && objectEqualityComparator.equals(this.b, pair.b);
    }

    public int hashCode() {
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(MurmurHash.initialize(), this.a), this.b), 2);
    }

    public String toString() {
        return String.format("(%s, %s)", this.a, this.b);
    }
}
