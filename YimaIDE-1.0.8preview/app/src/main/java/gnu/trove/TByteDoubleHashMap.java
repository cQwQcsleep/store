package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteDoubleHashMap extends TByteHash {
    protected transient double[] _values;

    public static final class EqProcedure implements TByteDoubleProcedure {
        private final TByteDoubleHashMap _otherMap;

        public EqProcedure(TByteDoubleHashMap tByteDoubleHashMap) {
            this._otherMap = tByteDoubleHashMap;
        }

        private static boolean eq(double d, double d2) {
            return d == d2;
        }

        @Override // gnu.trove.TByteDoubleProcedure
        public final boolean execute(byte b, double d) {
            return this._otherMap.index(b) >= 0 && eq(d, this._otherMap.get(b));
        }
    }

    public final class HashProcedure implements TByteDoubleProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TByteDoubleProcedure
        public final boolean execute(byte b, double d) {
            this.h += TByteDoubleHashMap.this._hashingStrategy.computeHashCode(b) ^ HashFunctions.hash(d);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TByteDoubleHashMap() {
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        setUp(i);
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            put(objectInputStream.readByte(), objectInputStream.readDouble());
            i = i2;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this._size);
        SerializationProcedure serializationProcedure = new SerializationProcedure(objectOutputStream);
        if (!forEachEntry(serializationProcedure)) {
            throw serializationProcedure.exception;
        }
    }

    public boolean adjustValue(byte b, double d) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return false;
        }
        double[] dArr = this._values;
        dArr[iIndex] = dArr[iIndex] + d;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        byte[] bArr = this._set;
        double[] dArr = this._values;
        if (dArr == null) {
            return;
        }
        byte[] bArr2 = this._states;
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            bArr[i] = 0;
            dArr[i] = 0.0d;
            bArr2[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TByteDoubleHashMap tByteDoubleHashMap = (TByteDoubleHashMap) super.clone();
        double[] dArr = this._values;
        tByteDoubleHashMap._values = dArr == null ? null : (double[]) dArr.clone();
        return tByteDoubleHashMap;
    }

    public boolean containsKey(byte b) {
        return contains(b);
    }

    public boolean containsValue(double d) {
        byte[] bArr = this._states;
        double[] dArr = this._values;
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            if (bArr[i] == 1 && d == dArr[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TByteDoubleHashMap)) {
            return false;
        }
        TByteDoubleHashMap tByteDoubleHashMap = (TByteDoubleHashMap) obj;
        if (tByteDoubleHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tByteDoubleHashMap));
    }

    public boolean forEachEntry(TByteDoubleProcedure tByteDoubleProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        double[] dArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tByteDoubleProcedure.execute(bArr2[i], dArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public boolean forEachKey(TByteProcedure tByteProcedure) {
        return forEach(tByteProcedure);
    }

    public boolean forEachValue(TDoubleProcedure tDoubleProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._values;
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

    public double get(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return 0.0d;
        }
        return this._values[iIndex];
    }

    public double[] getValues() {
        double[] dArr = new double[size()];
        double[] dArr2 = this._values;
        byte[] bArr = this._states;
        if (bArr != null) {
            int length = bArr.length;
            int i = 0;
            while (true) {
                int i2 = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i2] == 1) {
                    dArr[i] = dArr2[i2];
                    i++;
                }
                length = i2;
            }
        }
        return dArr;
    }

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEachEntry(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public boolean increment(byte b) {
        return adjustValue(b, 1.0d);
    }

    public TByteDoubleIterator iterator() {
        return new TByteDoubleIterator(this);
    }

    public byte[] keys() {
        byte[] bArr = new byte[size()];
        byte[] bArr2 = this._set;
        byte[] bArr3 = this._states;
        if (bArr3 != null) {
            int length = bArr3.length;
            int i = 0;
            while (true) {
                int i2 = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr3[i2] == 1) {
                    bArr[i] = bArr2[i2];
                    i++;
                }
                length = i2;
            }
        }
        return bArr;
    }

    public double put(byte b, double d) {
        double d2;
        boolean z;
        int iInsertionIndex = insertionIndex(b);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            d2 = this._values[iInsertionIndex];
            z = false;
        } else {
            d2 = 0.0d;
            z = true;
        }
        byte[] bArr = this._states;
        byte b2 = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = b;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = d;
        if (z) {
            postInsertHook(b2 == 0);
        }
        return d2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        byte[] bArr = this._set;
        double[] dArr = this._values;
        byte[] bArr2 = this._states;
        this._set = new byte[i];
        this._values = new double[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr2[i2] == 1) {
                byte b = bArr[i2];
                int iInsertionIndex = insertionIndex(b);
                this._set[iInsertionIndex] = b;
                this._values[iInsertionIndex] = dArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public double remove(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return 0.0d;
        }
        double d = this._values[iIndex];
        removeAt(iIndex);
        return d;
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0d;
        super.removeAt(i);
    }

    public boolean retainEntries(TByteDoubleProcedure tByteDoubleProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        double[] dArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tByteDoubleProcedure.execute(bArr2[length], dArr[length])) {
                    removeAt(length);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? null : new double[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TByteDoubleProcedure() { // from class: gnu.trove.TByteDoubleHashMap.1
            @Override // gnu.trove.TByteDoubleProcedure
            public boolean execute(byte b, double d) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append((int) b);
                sb.append('=');
                sb.append(d);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TDoubleFunction tDoubleFunction) {
        byte[] bArr = this._states;
        double[] dArr = this._values;
        if (bArr == null) {
            return;
        }
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            if (bArr[i] == 1) {
                dArr[i] = tDoubleFunction.execute(dArr[i]);
            }
            length = i;
        }
    }

    public TByteDoubleHashMap(int i) {
        super(i);
    }

    public TByteDoubleHashMap(int i, float f) {
        super(i, f);
    }

    public TByteDoubleHashMap(TByteHashingStrategy tByteHashingStrategy) {
        super(tByteHashingStrategy);
    }

    public TByteDoubleHashMap(int i, TByteHashingStrategy tByteHashingStrategy) {
        super(i, tByteHashingStrategy);
    }

    public TByteDoubleHashMap(int i, float f, TByteHashingStrategy tByteHashingStrategy) {
        super(i, f, tByteHashingStrategy);
    }
}
