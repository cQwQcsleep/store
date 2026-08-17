package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatIntIterator extends TPrimitiveIterator {
    private final TFloatIntHashMap _map;

    public TFloatIntIterator(TFloatIntHashMap tFloatIntHashMap) {
        super(tFloatIntHashMap);
        this._map = tFloatIntHashMap;
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

    public int setValue(int i) {
        int iValue = value();
        this._map._values[this._index] = i;
        return iValue;
    }

    public int value() {
        return this._map._values[this._index];
    }
}
