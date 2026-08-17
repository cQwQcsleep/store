package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class IdentityEquality<T> implements Equality<T> {
    @Override // gnu.trove.Equality
    public boolean equals(T t, T t2) {
        return t == t2;
    }
}
