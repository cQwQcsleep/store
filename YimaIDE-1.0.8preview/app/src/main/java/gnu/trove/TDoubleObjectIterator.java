package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleObjectIterator<V> extends TIterator {
    private final TDoubleObjectHashMap<V> _map;

    public TDoubleObjectIterator(TDoubleObjectHashMap<V> tDoubleObjectHashMap) {
        super(tDoubleObjectHashMap);
        this._map = tDoubleObjectHashMap;
    }

    public void advance() {
        moveToNextIndex();
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public double key() {
        return this._map._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public final int nextIndex() {
        int i;
        if (this._expectedSize != this._map.size()) {
            a1e.a();
            return 0;
        }
        V[] vArr = this._map._values;
        int i2 = this._index;
        while (true) {
            i = i2 - 1;
            if (i2 <= 0 || TDoubleObjectHashMap.isFull(vArr, i)) {
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

    public V setValue(V v) {
        V vValue = value();
        this._map._values[this._index] = v;
        return vValue;
    }

    public V value() {
        return this._map._values[this._index];
    }
}
