package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class TPrimitiveHash extends THash {
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    protected static final byte REMOVED = 2;
    protected transient byte[] _states;

    public TPrimitiveHash(int i) {
        this(i, 0.8f);
    }

    @Override // gnu.trove.THash
    public int capacity() {
        byte[] bArr = this._states;
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    @Override // gnu.trove.THash
    public Object clone() {
        TPrimitiveHash tPrimitiveHash = (TPrimitiveHash) super.clone();
        byte[] bArr = this._states;
        tPrimitiveHash._states = bArr == null ? null : (byte[]) bArr.clone();
        return tPrimitiveHash;
    }

    @Override // gnu.trove.THash
    public void removeAt(int i) {
        this._states[i] = REMOVED;
        super.removeAt(i);
    }

    @Override // gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._states = i == -1 ? null : new byte[up];
        return up;
    }

    public TPrimitiveHash() {
    }

    public TPrimitiveHash(int i, float f) {
        super(i, f);
    }
}
