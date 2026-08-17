package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteFloatHashMap extends TByteHash {
    protected transient float[] _values;

    public static final class EqProcedure implements TByteFloatProcedure {
        private final TByteFloatHashMap _otherMap;

        public EqProcedure(TByteFloatHashMap tByteFloatHashMap) {
            this._otherMap = tByteFloatHashMap;
        }

        private static boolean eq(float f, float f2) {
            return f == f2;
        }

        @Override // gnu.trove.TByteFloatProcedure
        public final boolean execute(byte b, float f) {
            return this._otherMap.index(b) >= 0 && eq(f, this._otherMap.get(b));
        }
    }

    public final class HashProcedure implements TByteFloatProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TByteFloatProcedure
        public final boolean execute(byte b, float f) {
            this.h += TByteFloatHashMap.this._hashingStrategy.computeHashCode(b) ^ HashFunctions.hash(f);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TByteFloatHashMap() {
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
            put(objectInputStream.readByte(), objectInputStream.readFloat());
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

    public boolean adjustValue(byte b, float f) {
        int iIndex = index(b);
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
        byte[] bArr = this._set;
        float[] fArr = this._values;
        if (fArr == null) {
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
            fArr[i] = 0.0f;
            bArr2[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TByteFloatHashMap tByteFloatHashMap = (TByteFloatHashMap) super.clone();
        float[] fArr = this._values;
        tByteFloatHashMap._values = fArr == null ? null : (float[]) fArr.clone();
        return tByteFloatHashMap;
    }

    public boolean containsKey(byte b) {
        return contains(b);
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
        if (!(obj instanceof TByteFloatHashMap)) {
            return false;
        }
        TByteFloatHashMap tByteFloatHashMap = (TByteFloatHashMap) obj;
        if (tByteFloatHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tByteFloatHashMap));
    }

    public boolean forEachEntry(TByteFloatProcedure tByteFloatProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        float[] fArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tByteFloatProcedure.execute(bArr2[i], fArr[i])) {
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

    public float get(byte b) {
        int iIndex = index(b);
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

    public boolean increment(byte b) {
        return adjustValue(b, 1.0f);
    }

    public TByteFloatIterator iterator() {
        return new TByteFloatIterator(this);
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

    public float put(byte b, float f) {
        float f2;
        boolean z;
        int iInsertionIndex = insertionIndex(b);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            f2 = this._values[iInsertionIndex];
            z = false;
        } else {
            f2 = 0.0f;
            z = true;
        }
        byte[] bArr = this._states;
        byte b2 = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = b;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = f;
        if (z) {
            postInsertHook(b2 == 0);
        }
        return f2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        byte[] bArr = this._set;
        float[] fArr = this._values;
        byte[] bArr2 = this._states;
        this._set = new byte[i];
        this._values = new float[i];
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
                this._values[iInsertionIndex] = fArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public float remove(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return 0.0f;
        }
        float f = this._values[iIndex];
        removeAt(iIndex);
        return f;
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0f;
        super.removeAt(i);
    }

    public boolean retainEntries(TByteFloatProcedure tByteFloatProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        float[] fArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tByteFloatProcedure.execute(bArr2[length], fArr[length])) {
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
        this._values = i == -1 ? null : new float[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TByteFloatProcedure() { // from class: gnu.trove.TByteFloatHashMap.1
            @Override // gnu.trove.TByteFloatProcedure
            public boolean execute(byte b, float f) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append((int) b);
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

    public TByteFloatHashMap(int i) {
        super(i);
    }

    public TByteFloatHashMap(int i, float f) {
        super(i, f);
    }

    public TByteFloatHashMap(TByteHashingStrategy tByteHashingStrategy) {
        super(tByteHashingStrategy);
    }

    public TByteFloatHashMap(int i, TByteHashingStrategy tByteHashingStrategy) {
        super(i, tByteHashingStrategy);
    }

    public TByteFloatHashMap(int i, float f, TByteHashingStrategy tByteHashingStrategy) {
        super(i, f, tByteHashingStrategy);
    }
}
