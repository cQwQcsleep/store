package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatDoubleHashMap extends TFloatHash {
    protected transient double[] _values;

    public static final class EqProcedure implements TFloatDoubleProcedure {
        private final TFloatDoubleHashMap _otherMap;

        public EqProcedure(TFloatDoubleHashMap tFloatDoubleHashMap) {
            this._otherMap = tFloatDoubleHashMap;
        }

        private static boolean eq(double d, double d2) {
            return d == d2;
        }

        @Override // gnu.trove.TFloatDoubleProcedure
        public final boolean execute(float f, double d) {
            return this._otherMap.index(f) >= 0 && eq(d, this._otherMap.get(f));
        }
    }

    public final class HashProcedure implements TFloatDoubleProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TFloatDoubleProcedure
        public final boolean execute(float f, double d) {
            this.h += TFloatDoubleHashMap.this._hashingStrategy.computeHashCode(f) ^ HashFunctions.hash(d);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TFloatDoubleHashMap() {
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
            put(objectInputStream.readFloat(), objectInputStream.readDouble());
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

    public boolean adjustValue(float f, double d) {
        int iIndex = index(f);
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
        float[] fArr = this._set;
        double[] dArr = this._values;
        if (dArr == null) {
            return;
        }
        byte[] bArr = this._states;
        int length = fArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            fArr[i] = 0.0f;
            dArr[i] = 0.0d;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TFloatDoubleHashMap tFloatDoubleHashMap = (TFloatDoubleHashMap) super.clone();
        double[] dArr = this._values;
        tFloatDoubleHashMap._values = dArr == null ? null : (double[]) dArr.clone();
        return tFloatDoubleHashMap;
    }

    public boolean containsKey(float f) {
        return contains(f);
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
        if (!(obj instanceof TFloatDoubleHashMap)) {
            return false;
        }
        TFloatDoubleHashMap tFloatDoubleHashMap = (TFloatDoubleHashMap) obj;
        if (tFloatDoubleHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tFloatDoubleHashMap));
    }

    public boolean forEachEntry(TFloatDoubleProcedure tFloatDoubleProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._set;
        double[] dArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tFloatDoubleProcedure.execute(fArr[i], dArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public boolean forEachKey(TFloatProcedure tFloatProcedure) {
        return forEach(tFloatProcedure);
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

    public double get(float f) {
        int iIndex = index(f);
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

    public boolean increment(float f) {
        return adjustValue(f, 1.0d);
    }

    public TFloatDoubleIterator iterator() {
        return new TFloatDoubleIterator(this);
    }

    public float[] keys() {
        float[] fArr = new float[size()];
        float[] fArr2 = this._set;
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
                    fArr[i] = fArr2[i2];
                    i++;
                }
                length = i2;
            }
        }
        return fArr;
    }

    public double put(float f, double d) {
        double d2;
        boolean z;
        int iInsertionIndex = insertionIndex(f);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            d2 = this._values[iInsertionIndex];
            z = false;
        } else {
            d2 = 0.0d;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = f;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = d;
        if (z) {
            postInsertHook(b == 0);
        }
        return d2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        float[] fArr = this._set;
        double[] dArr = this._values;
        byte[] bArr = this._states;
        this._set = new float[i];
        this._values = new double[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr[i2] == 1) {
                float f = fArr[i2];
                int iInsertionIndex = insertionIndex(f);
                this._set[iInsertionIndex] = f;
                this._values[iInsertionIndex] = dArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public double remove(float f) {
        int iIndex = index(f);
        if (iIndex < 0) {
            return 0.0d;
        }
        double d = this._values[iIndex];
        removeAt(iIndex);
        return d;
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0d;
        super.removeAt(i);
    }

    public boolean retainEntries(TFloatDoubleProcedure tFloatDoubleProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._set;
        double[] dArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tFloatDoubleProcedure.execute(fArr[length], dArr[length])) {
                    removeAt(length);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? null : new double[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TFloatDoubleProcedure() { // from class: gnu.trove.TFloatDoubleHashMap.1
            @Override // gnu.trove.TFloatDoubleProcedure
            public boolean execute(float f, double d) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(f);
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

    public TFloatDoubleHashMap(int i) {
        super(i);
    }

    public TFloatDoubleHashMap(int i, float f) {
        super(i, f);
    }

    public TFloatDoubleHashMap(TFloatHashingStrategy tFloatHashingStrategy) {
        super(tFloatHashingStrategy);
    }

    public TFloatDoubleHashMap(int i, TFloatHashingStrategy tFloatHashingStrategy) {
        super(i, tFloatHashingStrategy);
    }

    public TFloatDoubleHashMap(int i, float f, TFloatHashingStrategy tFloatHashingStrategy) {
        super(i, f, tFloatHashingStrategy);
    }
}
