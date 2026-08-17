package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatFloatHashMap extends TFloatHash {
    protected transient float[] _values;

    public static final class EqProcedure implements TFloatFloatProcedure {
        private final TFloatFloatHashMap _otherMap;

        public EqProcedure(TFloatFloatHashMap tFloatFloatHashMap) {
            this._otherMap = tFloatFloatHashMap;
        }

        private static boolean eq(float f, float f2) {
            return f == f2;
        }

        @Override // gnu.trove.TFloatFloatProcedure
        public final boolean execute(float f, float f2) {
            return this._otherMap.index(f) >= 0 && eq(f2, this._otherMap.get(f));
        }
    }

    public final class HashProcedure implements TFloatFloatProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TFloatFloatProcedure
        public final boolean execute(float f, float f2) {
            this.h += TFloatFloatHashMap.this._hashingStrategy.computeHashCode(f) ^ HashFunctions.hash(f2);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TFloatFloatHashMap() {
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
            put(objectInputStream.readFloat(), objectInputStream.readFloat());
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

    public boolean adjustValue(float f, float f2) {
        int iIndex = index(f);
        if (iIndex < 0) {
            return false;
        }
        float[] fArr = this._values;
        fArr[iIndex] = fArr[iIndex] + f2;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        float[] fArr = this._set;
        float[] fArr2 = this._values;
        if (fArr2 == null) {
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
            fArr2[i] = 0.0f;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TFloatFloatHashMap tFloatFloatHashMap = (TFloatFloatHashMap) super.clone();
        float[] fArr = this._values;
        tFloatFloatHashMap._values = fArr == null ? null : (float[]) fArr.clone();
        return tFloatFloatHashMap;
    }

    public boolean containsKey(float f) {
        return contains(f);
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
        if (!(obj instanceof TFloatFloatHashMap)) {
            return false;
        }
        TFloatFloatHashMap tFloatFloatHashMap = (TFloatFloatHashMap) obj;
        if (tFloatFloatHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tFloatFloatHashMap));
    }

    public boolean forEachEntry(TFloatFloatProcedure tFloatFloatProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._set;
        float[] fArr2 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tFloatFloatProcedure.execute(fArr[i], fArr2[i])) {
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

    public float get(float f) {
        int iIndex = index(f);
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

    public boolean increment(float f) {
        return adjustValue(f, 1.0f);
    }

    public TFloatFloatIterator iterator() {
        return new TFloatFloatIterator(this);
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

    public float put(float f, float f2) {
        float f3;
        boolean z;
        int iInsertionIndex = insertionIndex(f);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            f3 = this._values[iInsertionIndex];
            z = false;
        } else {
            f3 = 0.0f;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = f;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = f2;
        if (z) {
            postInsertHook(b == 0);
        }
        return f3;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        float[] fArr = this._set;
        float[] fArr2 = this._values;
        byte[] bArr = this._states;
        this._set = new float[i];
        this._values = new float[i];
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
                this._values[iInsertionIndex] = fArr2[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public float remove(float f) {
        int iIndex = index(f);
        if (iIndex < 0) {
            return 0.0f;
        }
        float f2 = this._values[iIndex];
        removeAt(iIndex);
        return f2;
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0f;
        super.removeAt(i);
    }

    public boolean retainEntries(TFloatFloatProcedure tFloatFloatProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._set;
        float[] fArr2 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tFloatFloatProcedure.execute(fArr[length], fArr2[length])) {
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
        this._values = i == -1 ? null : new float[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TFloatFloatProcedure() { // from class: gnu.trove.TFloatFloatHashMap.1
            @Override // gnu.trove.TFloatFloatProcedure
            public boolean execute(float f, float f2) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(f);
                sb.append('=');
                sb.append(f2);
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

    public TFloatFloatHashMap(int i) {
        super(i);
    }

    public TFloatFloatHashMap(int i, float f) {
        super(i, f);
    }

    public TFloatFloatHashMap(TFloatHashingStrategy tFloatHashingStrategy) {
        super(tFloatHashingStrategy);
    }

    public TFloatFloatHashMap(int i, TFloatHashingStrategy tFloatHashingStrategy) {
        super(i, tFloatHashingStrategy);
    }

    public TFloatFloatHashMap(int i, float f, TFloatHashingStrategy tFloatHashingStrategy) {
        super(i, f, tFloatHashingStrategy);
    }
}
