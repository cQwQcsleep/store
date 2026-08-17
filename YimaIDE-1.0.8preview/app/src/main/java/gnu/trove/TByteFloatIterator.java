package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteFloatIterator extends TPrimitiveIterator {
    private final TByteFloatHashMap _map;

    public TByteFloatIterator(TByteFloatHashMap tByteFloatHashMap) {
        super(tByteFloatHashMap);
        this._map = tByteFloatHashMap;
    }

    public void advance() {
        moveToNextIndex();
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public byte key() {
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
