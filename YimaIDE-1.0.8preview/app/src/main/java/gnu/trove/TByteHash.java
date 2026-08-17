package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class TByteHash extends TPrimitiveHash implements TByteHashingStrategy {
    protected final TByteHashingStrategy _hashingStrategy;
    protected transient byte[] _set;

    public TByteHash() {
        this._hashingStrategy = this;
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TByteHash tByteHash = (TByteHash) super.clone();
        byte[] bArr = this._set;
        tByteHash._set = bArr == null ? null : (byte[]) bArr.clone();
        return tByteHash;
    }

    @Override // gnu.trove.TByteHashingStrategy
    public final int computeHashCode(byte b) {
        return HashFunctions.hash((int) b);
    }

    public boolean contains(byte b) {
        return index(b) >= 0;
    }

    public boolean forEach(TByteProcedure tByteProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tByteProcedure.execute(bArr2[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public int index(byte b) {
        byte[] bArr = this._states;
        if (bArr == null) {
            return -1;
        }
        byte[] bArr2 = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(b) & Integer.MAX_VALUE;
        int i = iComputeHashCode % length;
        byte b2 = bArr[i];
        if (b2 != 0 && (b2 == 2 || bArr2[i] != b)) {
            int i2 = (iComputeHashCode % (length - 2)) + 1;
            while (true) {
                i -= i2;
                if (i < 0) {
                    i += length;
                }
                byte b3 = bArr[i];
                if (b3 == 0 || (b3 != 2 && bArr2[i] == b)) {
                    break;
                }
            }
        }
        if (bArr[i] == 0) {
            return -1;
        }
        return i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x004f, code lost:
    
        if (r4 == 1) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int insertionIndex(byte b) {
        byte b2;
        byte b3;
        int i;
        if (this._set == null) {
            setUp(6);
        }
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(b) & Integer.MAX_VALUE;
        int i2 = iComputeHashCode % length;
        byte b4 = bArr[i2];
        if (b4 == 0) {
            return i2;
        }
        if (b4 != 1 || bArr2[i2] != b) {
            int i3 = (iComputeHashCode % (length - 2)) + 1;
            do {
                i2 -= i3;
                if (i2 < 0) {
                    i2 += length;
                }
                b2 = bArr[i2];
                if (b2 != 1) {
                    break;
                }
            } while (bArr2[i2] != b);
            if (b2 == 2) {
                int i4 = i2;
                while (true) {
                    b3 = bArr[i4];
                    if (b3 == 0 || (b3 != 2 && bArr2[i4] == b)) {
                        break;
                    }
                    i4 -= i3;
                    if (i4 < 0) {
                        i4 += length;
                    }
                }
                if (b3 == 1) {
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
        this._set = i == -1 ? null : new byte[up];
        return up;
    }

    public TByteHash(int i) {
        super(i);
        this._hashingStrategy = this;
    }

    public TByteHash(int i, float f) {
        super(i, f);
        this._hashingStrategy = this;
    }

    public TByteHash(TByteHashingStrategy tByteHashingStrategy) {
        this._hashingStrategy = tByteHashingStrategy;
    }

    public TByteHash(int i, TByteHashingStrategy tByteHashingStrategy) {
        super(i);
        this._hashingStrategy = tByteHashingStrategy;
    }

    public TByteHash(int i, float f, TByteHashingStrategy tByteHashingStrategy) {
        super(i, f);
        this._hashingStrategy = tByteHashingStrategy;
    }
}
