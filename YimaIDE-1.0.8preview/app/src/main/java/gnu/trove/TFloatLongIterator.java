package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatLongIterator extends TPrimitiveIterator {
    private final TFloatLongHashMap _map;

    public TFloatLongIterator(TFloatLongHashMap tFloatLongHashMap) {
        super(tFloatLongHashMap);
        this._map = tFloatLongHashMap;
    }

    public void advance() {
        moveToNextIndex();
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public float key() {
        return this._map._set[this._index];
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
