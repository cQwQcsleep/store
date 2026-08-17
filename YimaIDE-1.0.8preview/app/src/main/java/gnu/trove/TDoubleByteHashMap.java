package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleByteHashMap extends TDoubleHash {
    protected transient byte[] _values;

    public static final class EqProcedure implements TDoubleByteProcedure {
        private final TDoubleByteHashMap _otherMap;

        public EqProcedure(TDoubleByteHashMap tDoubleByteHashMap) {
            this._otherMap = tDoubleByteHashMap;
        }

        private static boolean eq(byte b, byte b2) {
            return b == b2;
        }

        @Override // gnu.trove.TDoubleByteProcedure
        public final boolean execute(double d, byte b) {
            return this._otherMap.index(d) >= 0 && eq(b, this._otherMap.get(d));
        }
    }

    public final class HashProcedure implements TDoubleByteProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TDoubleByteProcedure
        public final boolean execute(double d, byte b) {
            this.h += TDoubleByteHashMap.this._hashingStrategy.computeHashCode(d) ^ HashFunctions.hash((int) b);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TDoubleByteHashMap() {
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
            put(objectInputStream.readDouble(), objectInputStream.readByte());
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

    public boolean adjustValue(double d, byte b) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return false;
        }
        byte[] bArr = this._values;
        bArr[iIndex] = (byte) (bArr[iIndex] + b);
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        double[] dArr = this._set;
        byte[] bArr = this._values;
        if (bArr == null) {
            return;
        }
        byte[] bArr2 = this._states;
        int length = dArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            dArr[i] = 0.0d;
            bArr[i] = 0;
            bArr2[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TDoubleByteHashMap tDoubleByteHashMap = (TDoubleByteHashMap) super.clone();
        byte[] bArr = this._values;
        tDoubleByteHashMap._values = bArr == null ? null : (byte[]) bArr.clone();
        return tDoubleByteHashMap;
    }

    public boolean containsKey(double d) {
        return contains(d);
    }

    public boolean containsValue(byte b) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._values;
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            if (bArr[i] == 1 && b == bArr2[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TDoubleByteHashMap)) {
            return false;
        }
        TDoubleByteHashMap tDoubleByteHashMap = (TDoubleByteHashMap) obj;
        if (tDoubleByteHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tDoubleByteHashMap));
    }

    public boolean forEachEntry(TDoubleByteProcedure tDoubleByteProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        byte[] bArr2 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tDoubleByteProcedure.execute(dArr[i], bArr2[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public boolean forEachKey(TDoubleProcedure tDoubleProcedure) {
        return forEach(tDoubleProcedure);
    }

    public boolean forEachValue(TByteProcedure tByteProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._values;
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

    public byte get(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return (byte) 0;
        }
        return this._values[iIndex];
    }

    public byte[] getValues() {
        byte[] bArr = new byte[size()];
        byte[] bArr2 = this._values;
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

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEachEntry(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public boolean increment(double d) {
        return adjustValue(d, (byte) 1);
    }

    public TDoubleByteIterator iterator() {
        return new TDoubleByteIterator(this);
    }

    public double[] keys() {
        double[] dArr = new double[size()];
        double[] dArr2 = this._set;
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

    public byte put(double d, byte b) {
        byte b2;
        boolean z;
        int iInsertionIndex = insertionIndex(d);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            b2 = this._values[iInsertionIndex];
            z = false;
        } else {
            b2 = 0;
            z = true;
        }
        byte[] bArr = this._states;
        byte b3 = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = d;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = b;
        if (z) {
            postInsertHook(b3 == 0);
        }
        return b2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        double[] dArr = this._set;
        byte[] bArr = this._values;
        byte[] bArr2 = this._states;
        this._set = new double[i];
        this._values = new byte[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr2[i2] == 1) {
                double d = dArr[i2];
                int iInsertionIndex = insertionIndex(d);
                this._set[iInsertionIndex] = d;
                this._values[iInsertionIndex] = bArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public byte remove(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return (byte) 0;
        }
        byte b = this._values[iIndex];
        removeAt(iIndex);
        return b;
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TDoubleByteProcedure tDoubleByteProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        byte[] bArr2 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tDoubleByteProcedure.execute(dArr[length], bArr2[length])) {
                    removeAt(length);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? null : new byte[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TDoubleByteProcedure() { // from class: gnu.trove.TDoubleByteHashMap.1
            @Override // gnu.trove.TDoubleByteProcedure
            public boolean execute(double d, byte b) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(d);
                sb.append('=');
                sb.append((int) b);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TByteFunction tByteFunction) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._values;
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
                bArr2[i] = tByteFunction.execute(bArr2[i]);
            }
            length = i;
        }
    }

    public TDoubleByteHashMap(int i) {
        super(i);
    }

    public TDoubleByteHashMap(int i, float f) {
        super(i, f);
    }

    public TDoubleByteHashMap(TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(tDoubleHashingStrategy);
    }

    public TDoubleByteHashMap(int i, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, tDoubleHashingStrategy);
    }

    public TDoubleByteHashMap(int i, float f, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, f, tDoubleHashingStrategy);
    }
}
