package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleFloatHashMap extends TDoubleHash {
    protected transient float[] _values;

    public static final class EqProcedure implements TDoubleFloatProcedure {
        private final TDoubleFloatHashMap _otherMap;

        public EqProcedure(TDoubleFloatHashMap tDoubleFloatHashMap) {
            this._otherMap = tDoubleFloatHashMap;
        }

        private static boolean eq(float f, float f2) {
            return f == f2;
        }

        @Override // gnu.trove.TDoubleFloatProcedure
        public final boolean execute(double d, float f) {
            return this._otherMap.index(d) >= 0 && eq(f, this._otherMap.get(d));
        }
    }

    public final class HashProcedure implements TDoubleFloatProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TDoubleFloatProcedure
        public final boolean execute(double d, float f) {
            this.h += TDoubleFloatHashMap.this._hashingStrategy.computeHashCode(d) ^ HashFunctions.hash(f);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TDoubleFloatHashMap() {
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
            put(objectInputStream.readDouble(), objectInputStream.readFloat());
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

    public boolean adjustValue(double d, float f) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return false;
        }
        float[] fArr = this._values;
        fArr[iIndex] = fArr[iIndex] + f;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        double[] dArr = this._set;
        float[] fArr = this._values;
        if (fArr == null) {
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
            fArr[i] = 0.0f;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TDoubleFloatHashMap tDoubleFloatHashMap = (TDoubleFloatHashMap) super.clone();
        float[] fArr = this._values;
        tDoubleFloatHashMap._values = fArr == null ? null : (float[]) fArr.clone();
        return tDoubleFloatHashMap;
    }

    public boolean containsKey(double d) {
        return contains(d);
    }

    public boolean containsValue(float f) {
        byte[] bArr = this._states;
        float[] fArr = this._values;
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            if (bArr[i] == 1 && f == fArr[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TDoubleFloatHashMap)) {
            return false;
        }
        TDoubleFloatHashMap tDoubleFloatHashMap = (TDoubleFloatHashMap) obj;
        if (tDoubleFloatHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tDoubleFloatHashMap));
    }

    public boolean forEachEntry(TDoubleFloatProcedure tDoubleFloatProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        float[] fArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tDoubleFloatProcedure.execute(dArr[i], fArr[i])) {
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

    public boolean forEachValue(TFloatProcedure tFloatProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tFloatProcedure.execute(fArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public float get(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return 0.0f;
        }
        return this._values[iIndex];
    }

    public float[] getValues() {
        float[] fArr = new float[size()];
        float[] fArr2 = this._values;
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

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEachEntry(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public boolean increment(double d) {
        return adjustValue(d, 1.0f);
    }

    public TDoubleFloatIterator iterator() {
        return new TDoubleFloatIterator(this);
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

    public float put(double d, float f) {
        float f2;
        boolean z;
        int iInsertionIndex = insertionIndex(d);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            f2 = this._values[iInsertionIndex];
            z = false;
        } else {
            f2 = 0.0f;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = d;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = f;
        if (z) {
            postInsertHook(b == 0);
        }
        return f2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        double[] dArr = this._set;
        float[] fArr = this._values;
        byte[] bArr = this._states;
        this._set = new double[i];
        this._values = new float[i];
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
                this._values[iInsertionIndex] = fArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public float remove(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return 0.0f;
        }
        float f = this._values[iIndex];
        removeAt(iIndex);
        return f;
    }

    @Override // gnu.trove.TDoubleHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0f;
        super.removeAt(i);
    }

    public boolean retainEntries(TDoubleFloatProcedure tDoubleFloatProcedure) {
        byte[] bArr = this._states;
        double[] dArr = this._set;
        float[] fArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tDoubleFloatProcedure.execute(dArr[length], fArr[length])) {
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
        this._values = i == -1 ? null : new float[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TDoubleFloatProcedure() { // from class: gnu.trove.TDoubleFloatHashMap.1
            @Override // gnu.trove.TDoubleFloatProcedure
            public boolean execute(double d, float f) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(d);
                sb.append('=');
                sb.append(f);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TFloatFunction tFloatFunction) {
        byte[] bArr = this._states;
        float[] fArr = this._values;
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
                fArr[i] = tFloatFunction.execute(fArr[i]);
            }
            length = i;
        }
    }

    public TDoubleFloatHashMap(int i) {
        super(i);
    }

    public TDoubleFloatHashMap(int i, float f) {
        super(i, f);
    }

    public TDoubleFloatHashMap(TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(tDoubleHashingStrategy);
    }

    public TDoubleFloatHashMap(int i, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, tDoubleHashingStrategy);
    }

    public TDoubleFloatHashMap(int i, float f, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, f, tDoubleHashingStrategy);
    }
}
