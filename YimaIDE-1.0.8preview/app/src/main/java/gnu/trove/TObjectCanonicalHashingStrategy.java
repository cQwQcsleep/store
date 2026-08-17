package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class TObjectCanonicalHashingStrategy<T> implements TObjectHashingStrategy<T> {
    @Override // gnu.trove.TObjectHashingStrategy
    public int computeHashCode(T t) {
        if (t != null) {
            return t.hashCode();
        }
        return 0;
    }

    @Override // gnu.trove.TObjectHashingStrategy, gnu.trove.Equality
    public boolean equals(T t, T t2) {
        if (t != null) {
            return t.equals(t2);
        }
        return t2 == null;
    }
}
