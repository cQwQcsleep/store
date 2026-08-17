package org.antlr.v4.runtime.misc;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class Triple<A, B, C> {
    public final A a;
    public final B b;
    public final C c;

    public Triple(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Triple)) {
            return false;
        }
        Triple triple = (Triple) obj;
        ObjectEqualityComparator objectEqualityComparator = ObjectEqualityComparator.INSTANCE;
        return objectEqualityComparator.equals(this.a, triple.a) && objectEqualityComparator.equals(this.b, triple.b) && objectEqualityComparator.equals(this.c, triple.c);
    }

    public int hashCode() {
        return MurmurHash.finish(MurmurHash.update(MurmurHash.update(MurmurHash.update(MurmurHash.initialize(), this.a), this.b), this.c), 3);
    }

    public String toString() {
        return String.format("(%s, %s, %s)", this.a, this.b, this.c);
    }
}
