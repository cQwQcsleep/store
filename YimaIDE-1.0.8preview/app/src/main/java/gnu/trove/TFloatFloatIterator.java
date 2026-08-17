package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatFloatIterator extends TPrimitiveIterator {
    private final TFloatFloatHashMap _map;

    public TFloatFloatIterator(TFloatFloatHashMap tFloatFloatHashMap) {
        super(tFloatFloatHashMap);
        this._map = tFloatFloatHashMap;
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

    public float setValue(float f) {
        float fValue = value();
        this._map._values[this._index] = f;
        return fValue;
    }

    public float value() {
        return this._map._values[this._index];
    }
}
