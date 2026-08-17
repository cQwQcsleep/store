package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleIntHashMap extends TDoubleHash {
    protected transient int[] _values;

    public static final class EqProcedure implements TDoubleIntProcedure {
        private final TDoubleIntHashMap _otherMap;

        public EqProcedure(TDoubleIntHashMap tDoubleIntHashMap) {
            this._otherMap = tDoubleIntHashMap;
        }

        private static boolean eq(int i, int i2) {
            return i == i2;
        }

        @Override // gnu.trove.TDoubleIntProcedure
        public final boolean execute(double d, int i) {
            return this._otherMap.index(d) >= 0 && eq(i, this._otherMap.get(d));
        }
    }

    public final class HashProcedure implements TDoubleIntProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TDoubleIntProcedure
        public final boolean execute(double d, int i) {
            this.h += TDoubleIntHashMap.this._hashingStrategy.computeHashCode(d) ^ HashFunctions.hash(i);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TDoubleIntHashMap() {
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
            put(objectInputStream.readDouble(), objectInputStream.readInt());
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

    public boolean adjustValue(double d, int i) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return false;
        }
        int[] iArr = this._values;
        iArr[iIndex] = iArr[iIndex] + i;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        double[] dArr = this._set;
        int[] iArr = this._values;
        if (iArr == null) {
            return;
        }
        byte[] bArr = this._states;
        int length = dArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            dArr[i] = 0.0d;
            iArr[i] = 0;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TDoubleIntHashMap tDoubleIntHashMap = (TDoubleIntHashMap) super.clone();
        int[] iArr = this._values;
        tDoubleIntHashMap._values = iArr == null ? null : (int[]) iArr.clone();
        return tDoubleIntHashMap;
    }

    public boolean containsKey(double d) {
        return contains(d);
    }

    public boolean containsValue(int i) {
        byte[] bArr = this._states;
        int[] iArr = this._values;
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return false;
            }
            if (bArr[i2] == 1 && i == iArr[i2]) {
                return true;
            }
            length = i2;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TDoubleIntHashMap)) {
            return false;
        }
        TDoubleIntHashMap tDoubleIntHashMap = (TDoubleIntHashMap) obj;
        if (tDoubleIntHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tDoubleIntHashMap));
    }

    public boolean forEachEntry(TDoubleIntProcedure tDoubleIntProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        int[] iArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tDoubleIntProcedure.execute(dArr[i], iArr[i])) {
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

    public boolean forEachValue(TIntProcedure tIntProcedure) {
        byte[] bArr = this._states;
        int[] iArr = this._values;
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

    public int get(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return 0;
        }
        return this._values[iIndex];
    }

    public int[] getValues() {
        int[] iArr = new int[size()];
        int[] iArr2 = this._values;
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
                    iArr[i] = iArr2[i2];
                    i++;
                }
                length = i2;
            }
        }
        return iArr;
    }

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEachEntry(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public boolean increment(double d) {
        return adjustValue(d, 1);
    }

    public TDoubleIntIterator iterator() {
        return new TDoubleIntIterator(this);
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

    public int put(double d, int i) {
        int i2;
        boolean z;
        int iInsertionIndex = insertionIndex(d);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            i2 = this._values[iInsertionIndex];
            z = false;
        } else {
            i2 = 0;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = d;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = i;
        if (z) {
            postInsertHook(b == 0);
        }
        return i2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        double[] dArr = this._set;
        int[] iArr = this._values;
        byte[] bArr = this._states;
        this._set = new double[i];
        this._values = new int[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr[i2] == 1) {
                double d = dArr[i2];
                int iInsertionIndex = insertionIndex(d);
                this._set[iInsertionIndex] = d;
                this._values[iInsertionIndex] = iArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public int remove(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return 0;
        }
        int i = this._values[iIndex];
        removeAt(iIndex);
        return i;
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TDoubleIntProcedure tDoubleIntProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        int[] iArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tDoubleIntProcedure.execute(dArr[length], iArr[length])) {
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
        this._values = i == -1 ? null : new int[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TDoubleIntProcedure() { // from class: gnu.trove.TDoubleIntHashMap.1
            @Override // gnu.trove.TDoubleIntProcedure
            public boolean execute(double d, int i) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(d);
                sb.append('=');
                sb.append(i);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TIntFunction tIntFunction) {
        byte[] bArr = this._states;
        int[] iArr = this._values;
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
                iArr[i] = tIntFunction.execute(iArr[i]);
            }
            length = i;
        }
    }

    public TDoubleIntHashMap(int i) {
        super(i);
    }

    public TDoubleIntHashMap(int i, float f) {
        super(i, f);
    }

    public TDoubleIntHashMap(TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(tDoubleHashingStrategy);
    }

    public TDoubleIntHashMap(int i, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, tDoubleHashingStrategy);
    }

    public TDoubleIntHashMap(int i, float f, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, f, tDoubleHashingStrategy);
    }
}
