package org.antlr.v4.runtime.misc;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class ObjectEqualityComparator extends AbstractEqualityComparator<Object> {
    public static final ObjectEqualityComparator INSTANCE = new ObjectEqualityComparator();

    @Override // org.antlr.v4.runtime.misc.EqualityComparator
    public boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    @Override // org.antlr.v4.runtime.misc.EqualityComparator
    public int hashCode(Object obj) {
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }
}
