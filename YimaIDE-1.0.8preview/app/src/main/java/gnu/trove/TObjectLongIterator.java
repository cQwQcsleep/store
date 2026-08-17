package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TObjectLongIterator<K> extends TIterator {
    private final TObjectLongHashMap<K> _map;

    public TObjectLongIterator(TObjectLongHashMap<K> tObjectLongHashMap) {
        super(tObjectLongHashMap);
        this._map = tObjectLongHashMap;
    }

    public void advance() {
        moveToNextIndex();
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public K key() {
        return (K) this._map._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public final int nextIndex() {
        int i;
        Object obj;
        if (this._expectedSize != this._hash.size()) {
            a1e.a();
            return 0;
        }
        Object[] objArr = this._map._set;
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

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }

    public long setValue(long j) {
        long jValue = value();
        this._map._values[this._index] = j;
        return jValue;
    }

    public long value() {
        return this._map._values[this._index];
    }
}
