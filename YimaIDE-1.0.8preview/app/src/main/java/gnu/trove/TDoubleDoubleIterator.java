package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleDoubleIterator extends TPrimitiveIterator {
    private final TDoubleDoubleHashMap _map;

    public TDoubleDoubleIterator(TDoubleDoubleHashMap tDoubleDoubleHashMap) {
        super(tDoubleDoubleHashMap);
        this._map = tDoubleDoubleHashMap;
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
