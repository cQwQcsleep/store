package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class TPrimitiveIterator extends TIterator {
    protected final TPrimitiveHash _hash;

    public TPrimitiveIterator(TPrimitiveHash tPrimitiveHash) {
        super(tPrimitiveHash);
        this._hash = tPrimitiveHash;
    }

    @Override // gnu.trove.TIterator
    public final int nextIndex() {
        int i;
        if (this._expectedSize != this._hash.size()) {
            a1e.a();
            return 0;
        }
        byte[] bArr = this._hash._states;
        int i2 = this._index;
        while (true) {
            i = i2 - 1;
            if (i2 <= 0 || bArr[i] == 1) {
                break;
            }
            i2 = i;
        }
        return i;
    }
}
