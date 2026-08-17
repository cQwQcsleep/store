package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class TIntHash extends TPrimitiveHash implements TIntHashingStrategy {
    protected final TIntHashingStrategy _hashingStrategy;
    protected transient int[] _set;

    public TIntHash() {
        this._hashingStrategy = this;
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TIntHash tIntHash = (TIntHash) super.clone();
        int[] iArr = this._set;
        tIntHash._set = iArr == null ? null : (int[]) iArr.clone();
        return tIntHash;
    }

    @Override // gnu.trove.TIntHashingStrategy
    public final int computeHashCode(int i) {
        return HashFunctions.hash(i);
    }

    public boolean contains(int i) {
        return index(i) >= 0;
    }

    public boolean forEach(TIntProcedure tIntProcedure) {
        byte[] bArr = this._states;
        int[] iArr = this._set;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tIntProcedure.execute(iArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public int index(int i) {
        byte[] bArr = this._states;
        if (bArr == null) {
            return -1;
        }
        int[] iArr = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(i) & Integer.MAX_VALUE;
        int i2 = iComputeHashCode % length;
        byte b = bArr[i2];
        if (b != 0 && (b == 2 || iArr[i2] != i)) {
            int i3 = (iComputeHashCode % (length - 2)) + 1;
            while (true) {
                i2 -= i3;
                if (i2 < 0) {
                    i2 += length;
                }
                byte b2 = bArr[i2];
                if (b2 == 0 || (b2 != 2 && iArr[i2] == i)) {
                    break;
                }
            }
        }
        if (bArr[i2] == 0) {
            return -1;
        }
        return i2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:36:0x004f, code lost:
    
        if (r4 == 1) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public int insertionIndex(int i) {
        byte b;
        byte b2;
        int i2;
        if (this._set == null) {
            setUp(6);
        }
        byte[] bArr = this._states;
        int[] iArr = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(i) & Integer.MAX_VALUE;
        int i3 = iComputeHashCode % length;
        byte b3 = bArr[i3];
        if (b3 == 0) {
            return i3;
        }
        if (b3 != 1 || iArr[i3] != i) {
            int i4 = (iComputeHashCode % (length - 2)) + 1;
            do {
                i3 -= i4;
                if (i3 < 0) {
                    i3 += length;
                }
                b = bArr[i3];
                if (b != 1) {
                    break;
                }
            } while (iArr[i3] != i);
            if (b == 2) {
                int i5 = i3;
                while (true) {
                    b2 = bArr[i5];
                    if (b2 == 0 || (b2 != 2 && iArr[i5] == i)) {
                        break;
                    }
                    i5 -= i4;
                    if (i5 < 0) {
                        i5 += length;
                    }
                }
                if (b2 == 1) {
                    i2 = -i5;
                }
            }
            return i3;
        }
        i2 = -i3;
        return i2 - 1;
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._set[i] = 0;
        super.removeAt(i);
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._set = i == -1 ? null : new int[up];
        return up;
    }

    public TIntHash(int i) {
        super(i);
        this._hashingStrategy = this;
    }

    public TIntHash(int i, float f) {
        super(i, f);
        this._hashingStrategy = this;
    }

    public TIntHash(TIntHashingStrategy tIntHashingStrategy) {
        this._hashingStrategy = tIntHashingStrategy;
    }

    public TIntHash(int i, TIntHashingStrategy tIntHashingStrategy) {
        super(i);
        this._hashingStrategy = tIntHashingStrategy;
    }

    public TIntHash(int i, float f, TIntHashingStrategy tIntHashingStrategy) {
        super(i, f);
        this._hashingStrategy = tIntHashingStrategy;
    }
}
