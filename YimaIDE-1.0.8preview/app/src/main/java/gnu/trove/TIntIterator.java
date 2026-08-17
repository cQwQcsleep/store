package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TIntIterator extends TPrimitiveIterator {
    private final TIntHash _hash;

    public TIntIterator(TIntHash tIntHash) {
        super(tIntHash);
        this._hash = tIntHash;
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public int next() {
        moveToNextIndex();
        return this._hash._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }
}
