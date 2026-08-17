package gnu.trove;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
abstract class THashIterator<V> extends TIterator implements Iterator<V> {
    protected final TObjectHash _hash;

    public THashIterator(TObjectHash tObjectHash) {
        super(tObjectHash);
        this._hash = tObjectHash;
    }

    @Override // java.util.Iterator
    public V next() {
        moveToNextIndex();
        return objectAtIndex(this._index);
    }

    @Override // gnu.trove.TIterator
    public final int nextIndex() {
        int i;
        Object obj;
        if (this._expectedSize != this._hash.size()) {
            a1e.a();
            return 0;
        }
        Object[] objArr = this._hash._set;
        int i2 = this._index;
        while (true) {
            i = i2 - 1;
            if (i2 <= 0 || !((obj = objArr[i]) == null || obj == TObjectHash.REMOVED)) {
                break;
            }
            i2 = i;
        }
        return i;
    }

    public abstract V objectAtIndex(int i);
}
