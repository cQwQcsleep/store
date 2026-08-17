package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleDoubleHashMap extends TDoubleHash {
    protected transient double[] _values;

    public static final class EqProcedure implements TDoubleDoubleProcedure {
        private final TDoubleDoubleHashMap _otherMap;

        public EqProcedure(TDoubleDoubleHashMap tDoubleDoubleHashMap) {
            this._otherMap = tDoubleDoubleHashMap;
        }

        private static boolean eq(double d, double d2) {
            return d == d2;
        }

        @Override // gnu.trove.TDoubleDoubleProcedure
        public final boolean execute(double d, double d2) {
            return this._otherMap.index(d) >= 0 && eq(d2, this._otherMap.get(d));
        }
    }

    public final class HashProcedure implements TDoubleDoubleProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TDoubleDoubleProcedure
        public final boolean execute(double d, double d2) {
            this.h += TDoubleDoubleHashMap.this._hashingStrategy.computeHashCode(d) ^ HashFunctions.hash(d2);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TDoubleDoubleHashMap() {
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
            put(objectInputStream.readDouble(), objectInputStream.readDouble());
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

    public boolean adjustValue(double d, double d2) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return false;
        }
        double[] dArr = this._values;
        dArr[iIndex] = dArr[iIndex] + d2;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        double[] dArr = this._set;
        double[] dArr2 = this._values;
        if (dArr2 == null) {
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
            dArr2[i] = 0.0d;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TDoubleDoubleHashMap tDoubleDoubleHashMap = (TDoubleDoubleHashMap) super.clone();
        double[] dArr = this._values;
        tDoubleDoubleHashMap._values = dArr == null ? null : (double[]) dArr.clone();
        return tDoubleDoubleHashMap;
    }

    public boolean containsKey(double d) {
        return contains(d);
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
        if (!(obj instanceof TDoubleDoubleHashMap)) {
            return false;
        }
        TDoubleDoubleHashMap tDoubleDoubleHashMap = (TDoubleDoubleHashMap) obj;
        if (tDoubleDoubleHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tDoubleDoubleHashMap));
    }

    public boolean forEachEntry(TDoubleDoubleProcedure tDoubleDoubleProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        double[] dArr2 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tDoubleDoubleProcedure.execute(dArr[i], dArr2[i])) {
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

    public double get(double d) {
        int iIndex = index(d);
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

    public boolean increment(double d) {
        return adjustValue(d, 1.0d);
    }

    public TDoubleDoubleIterator iterator() {
        return new TDoubleDoubleIterator(this);
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

    public double put(double d, double d2) {
        double d3;
        boolean z;
        int iInsertionIndex = insertionIndex(d);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            d3 = this._values[iInsertionIndex];
            z = false;
        } else {
            d3 = 0.0d;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = d;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = d2;
        if (z) {
            postInsertHook(b == 0);
        }
        return d3;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        double[] dArr = this._set;
        double[] dArr2 = this._values;
        byte[] bArr = this._states;
        this._set = new double[i];
        this._values = new double[i];
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
                this._values[iInsertionIndex] = dArr2[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public double remove(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return 0.0d;
        }
        double d2 = this._values[iIndex];
        removeAt(iIndex);
        return d2;
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0d;
        super.removeAt(i);
    }

    public boolean retainEntries(TDoubleDoubleProcedure tDoubleDoubleProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        double[] dArr2 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tDoubleDoubleProcedure.execute(dArr[length], dArr2[length])) {
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
        this._values = i == -1 ? null : new double[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TDoubleDoubleProcedure() { // from class: gnu.trove.TDoubleDoubleHashMap.1
            @Override // gnu.trove.TDoubleDoubleProcedure
            public boolean execute(double d, double d2) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(d);
                sb.append('=');
                sb.append(d2);
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

    public TDoubleDoubleHashMap(int i) {
        super(i);
    }

    public TDoubleDoubleHashMap(int i, float f) {
        super(i, f);
    }

    public TDoubleDoubleHashMap(TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(tDoubleHashingStrategy);
    }

    public TDoubleDoubleHashMap(int i, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, tDoubleHashingStrategy);
    }

    public TDoubleDoubleHashMap(int i, float f, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, f, tDoubleHashingStrategy);
    }
}
