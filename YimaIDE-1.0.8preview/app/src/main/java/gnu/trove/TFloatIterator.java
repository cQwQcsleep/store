package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatIterator extends TPrimitiveIterator {
    private final TFloatHash _hash;

    public TFloatIterator(TFloatHash tFloatHash) {
        super(tFloatHash);
        this._hash = tFloatHash;
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public float next() {
        moveToNextIndex();
        return this._hash._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }
}
