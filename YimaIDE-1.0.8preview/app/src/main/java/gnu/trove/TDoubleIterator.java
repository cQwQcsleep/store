package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleIterator extends TPrimitiveIterator {
    private final TDoubleHash _hash;

    public TDoubleIterator(TDoubleHash tDoubleHash) {
        super(tDoubleHash);
        this._hash = tDoubleHash;
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public double next() {
        moveToNextIndex();
        return this._hash._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }
}
