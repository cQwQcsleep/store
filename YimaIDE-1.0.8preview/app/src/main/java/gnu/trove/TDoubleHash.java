package gnu.trove;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class TDoubleHash extends TPrimitiveHash implements TDoubleHashingStrategy {
    protected final TDoubleHashingStrategy _hashingStrategy;
    protected transient double[] _set;

    public TDoubleHash() {
        this._hashingStrategy = this;
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TDoubleHash tDoubleHash = (TDoubleHash) super.clone();
        double[] dArr = this._set;
        tDoubleHash._set = dArr == null ? null : (double[]) dArr.clone();
        return tDoubleHash;
    }

    @Override // gnu.trove.TDoubleHashingStrategy
    public final int computeHashCode(double d) {
        return HashFunctions.hash(d);
    }

    public boolean contains(double d) {
        return index(d) >= 0;
    }

    public boolean forEach(TDoubleProcedure tDoubleProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tDoubleProcedure.execute(dArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public int index(double d) {
        byte[] bArr = this._states;
        if (bArr == null) {
            return -1;
        }
        double[] dArr = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(d) & Integer.MAX_VALUE;
        int i = iComputeHashCode % length;
        byte b = bArr[i];
        if (b != 0 && (b == 2 || dArr[i] != d)) {
            int i2 = (iComputeHashCode % (length - 2)) + 1;
            while (true) {
                i -= i2;
                if (i < 0) {
                    i += length;
                }
                byte b2 = bArr[i];
                if (b2 == 0 || (b2 != 2 && dArr[i] == d)) {
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
    public int insertionIndex(double d) {
        byte b;
        byte b2;
        int i;
        if (this._set == null) {
            setUp(6);
        }
        byte[] bArr = this._states;
        double[] dArr = this._set;
        int length = bArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(d) & Integer.MAX_VALUE;
        int i2 = iComputeHashCode % length;
        byte b3 = bArr[i2];
        if (b3 == 0) {
            return i2;
        }
        if (b3 != 1 || dArr[i2] != d) {
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
            } while (dArr[i2] != d);
            if (b == 2) {
                int i4 = i2;
                while (true) {
                    b2 = bArr[i4];
                    if (b2 == 0 || (b2 != 2 && dArr[i4] == d)) {
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
        this._set[i] = 0.0d;
        super.removeAt(i);
    }

    @Override // gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._set = i == -1 ? null : new double[up];
        return up;
    }

    public TDoubleHash(int i) {
        super(i);
        this._hashingStrategy = this;
    }

    public TDoubleHash(int i, float f) {
        super(i, f);
        this._hashingStrategy = this;
    }

    public TDoubleHash(TDoubleHashingStrategy tDoubleHashingStrategy) {
        this._hashingStrategy = tDoubleHashingStrategy;
    }

    public TDoubleHash(int i, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i);
        this._hashingStrategy = tDoubleHashingStrategy;
    }

    public TDoubleHash(int i, float f, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, f);
        this._hashingStrategy = tDoubleHashingStrategy;
    }
}
