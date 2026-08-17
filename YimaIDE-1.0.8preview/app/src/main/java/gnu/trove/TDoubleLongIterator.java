package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleLongIterator extends TPrimitiveIterator {
    private final TDoubleLongHashMap _map;

    public TDoubleLongIterator(TDoubleLongHashMap tDoubleLongHashMap) {
        super(tDoubleLongHashMap);
        this._map = tDoubleLongHashMap;
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

    public long setValue(long j) {
        long jValue = value();
        this._map._values[this._index] = j;
        return jValue;
    }

    public long value() {
        return this._map._values[this._index];
    }
}
