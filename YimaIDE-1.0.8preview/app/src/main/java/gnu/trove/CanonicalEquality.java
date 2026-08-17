package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class CanonicalEquality<T> implements Equality<T> {
    @Override // gnu.trove.Equality
    public boolean equals(T t, T t2) {
        if (t != null) {
            return t.equals(t2);
        }
        return t2 == null;
    }
}
