package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteIterator extends TPrimitiveIterator {
    private final TByteHash _hash;

    public TByteIterator(TByteHash tByteHash) {
        super(tByteHash);
        this._hash = tByteHash;
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public byte next() {
        moveToNextIndex();
        return this._hash._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }
}
