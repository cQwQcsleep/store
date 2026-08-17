package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TIntByteIterator extends TPrimitiveIterator {
    private final TIntByteHashMap _map;

    public TIntByteIterator(TIntByteHashMap tIntByteHashMap) {
        super(tIntByteHashMap);
        this._map = tIntByteHashMap;
    }

    public void advance() {
        moveToNextIndex();
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ boolean hasNext() {
        return super.hasNext();
    }

    public int key() {
        return this._map._set[this._index];
    }

    @Override // gnu.trove.TIterator
    public /* bridge */ /* synthetic */ void remove() {
        super.remove();
    }

    public byte setValue(byte b) {
        byte bValue = value();
        this._map._values[this._index] = b;
        return bValue;
    }

    public byte value() {
        return this._map._values[this._index];
    }
}
