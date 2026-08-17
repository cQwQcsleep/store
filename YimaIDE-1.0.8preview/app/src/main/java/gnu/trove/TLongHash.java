package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class TLongHash extends TPrimitiveHash implements TLongHashingStrategy {
    protected final TLongHashingStrategy _hashingStrategy;
    protected transient long[] _set;

    public TLongHash() {
        this._hashingStrategy = this;
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TLongHash tLongHash = (TLongHash) super.clone();
        long[] jArr = this._set;
        tLongHash._set = jArr == null ? null : (long[]) jArr.clone();
        return tLongHash;
    }

    @Override // gnu.trove.TLongHashingStrategy
    public final int computeHashCode(long j) {
        return HashFunctions.hash(j);
    }

    public boolean contains(long j) {
        return index(j) >= 0;
    }

    public boolean forEach(TLongProcedure tLongProcedure) {
        byte[] bArr = this._states;
        long[] jArr = this._set;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tLongProcedure.execute(jArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public int index(long j) {
        byte[] bArr = this._states;
        if (bArr == null) {
            return -1;
        }
        long[] jArr = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(j) & Integer.MAX_VALUE;
        int i = iComputeHashCode % length;
        byte b = bArr[i];
        if (b != 0 && (b == 2 || jArr[i] != j)) {
            int i2 = (iComputeHashCode % (length - 2)) + 1;
            while (true) {
                i -= i2;
                if (i < 0) {
                    i += length;
                }
                byte b2 = bArr[i];
                if (b2 == 0 || (b2 != 2 && jArr[i] == j)) {
                    break;
                }
            }
        }
        if (bArr[i] == 0) {
            return -1;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x0055, code lost:
    
        if (r4 == 1) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int insertionIndex(long j) {
        byte b;
        byte b2;
        int i;
        if (this._set == null) {
            setUp(6);
        }
        byte[] bArr = this._states;
        long[] jArr = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(j) & Integer.MAX_VALUE;
        int i2 = iComputeHashCode % length;
        byte b3 = bArr[i2];
        if (b3 == 0) {
            return i2;
        }
        if (b3 != 1 || jArr[i2] != j) {
            int i3 = (iComputeHashCode % (length - 2)) + 1;
            do {
                i2 -= i3;
                if (i2 < 0) {
                    i2 += length;
                }
                b = bArr[i2];
                if (b != 1) {
                    break;
                }
            } while (jArr[i2] != j);
            if (b == 2) {
                int i4 = i2;
                while (true) {
                    b2 = bArr[i4];
                    if (b2 == 0 || (b2 != 2 && jArr[i4] == j)) {
                        break;
                    }
                    i4 -= i3;
                    if (i4 < 0) {
                        i4 += length;
                    }
                }
                if (b2 == 1) {
                    i = -i4;
                }
            }
            return i2;
        }
        i = -i2;
        return i - 1;
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._set[i] = 0;
        super.removeAt(i);
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._set = i == -1 ? null : new long[up];
        return up;
    }

    public TLongHash(int i) {
        super(i);
        this._hashingStrategy = this;
    }

    public TLongHash(int i, float f) {
        super(i, f);
        this._hashingStrategy = this;
    }

    public TLongHash(TLongHashingStrategy tLongHashingStrategy) {
        this._hashingStrategy = tLongHashingStrategy;
    }

    public TLongHash(int i, TLongHashingStrategy tLongHashingStrategy) {
        super(i);
        this._hashingStrategy = tLongHashingStrategy;
    }

    public TLongHash(int i, float f, TLongHashingStrategy tLongHashingStrategy) {
        super(i, f);
        this._hashingStrategy = tLongHashingStrategy;
    }
}
