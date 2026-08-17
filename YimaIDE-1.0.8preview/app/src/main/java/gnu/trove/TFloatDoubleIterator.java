package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatDoubleIterator extends TPrimitiveIterator {
    private final TFloatDoubleHashMap _map;

    public TFloatDoubleIterator(TFloatDoubleHashMap tFloatDoubleHashMap) {
        super(tFloatDoubleHashMap);
        this._map = tFloatDoubleHashMap;
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

    public double setValue(double d) {
        double dValue = value();
        this._map._values[this._index] = d;
        return dValue;
    }

    public double value() {
        return this._map._values[this._index];
    }
}
