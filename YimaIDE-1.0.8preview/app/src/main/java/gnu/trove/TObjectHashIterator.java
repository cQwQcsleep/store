package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
class TObjectHashIterator<E> extends THashIterator<E> {
    protected final TObjectHash<E> _objectHash;

    public TObjectHashIterator(TObjectHash<E> tObjectHash) {
        super(tObjectHash);
        this._objectHash = tObjectHash;
    }

    @Override // gnu.trove.THashIterator
    public E objectAtIndex(int i) {
        return (E) this._objectHash._set[i];
    }
}
