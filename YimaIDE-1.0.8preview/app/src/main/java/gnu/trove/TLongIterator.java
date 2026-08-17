package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TLongIterator extends TPrimitiveIterator {
    private final TLongHash _hash;

    public TLongIterator(TLongHash tLongHash) {
        super(tLongHash);
        this._hash = tLongHash;
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public long next() {
        moveToNextIndex();
        return this._hash._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }
}
