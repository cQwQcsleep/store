package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TFloatIntHashMap extends TFloatHash {
    protected transient int[] _values;

    public static final class EqProcedure implements TFloatIntProcedure {
        private final TFloatIntHashMap _otherMap;

        public EqProcedure(TFloatIntHashMap tFloatIntHashMap) {
            this._otherMap = tFloatIntHashMap;
        }

        private static boolean eq(int i, int i2) {
            return i == i2;
        }

        @Override // gnu.trove.TFloatIntProcedure
        public final boolean execute(float f, int i) {
            return this._otherMap.index(f) >= 0 && eq(i, this._otherMap.get(f));
        }
    }

    public final class HashProcedure implements TFloatIntProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TFloatIntProcedure
        public final boolean execute(float f, int i) {
            this.h += TFloatIntHashMap.this._hashingStrategy.computeHashCode(f) ^ HashFunctions.hash(i);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TFloatIntHashMap() {
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
            put(objectInputStream.readFloat(), objectInputStream.readInt());
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

    public boolean adjustValue(float f, int i) {
        int iIndex = index(f);
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
        float[] fArr = this._set;
        int[] iArr = this._values;
        if (iArr == null) {
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
            iArr[i] = 0;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TFloatIntHashMap tFloatIntHashMap = (TFloatIntHashMap) super.clone();
        int[] iArr = this._values;
        tFloatIntHashMap._values = iArr == null ? null : (int[]) iArr.clone();
        return tFloatIntHashMap;
    }

    public boolean containsKey(float f) {
        return contains(f);
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
        if (!(obj instanceof TFloatIntHashMap)) {
            return false;
        }
        TFloatIntHashMap tFloatIntHashMap = (TFloatIntHashMap) obj;
        if (tFloatIntHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tFloatIntHashMap));
    }

    public boolean forEachEntry(TFloatIntProcedure tFloatIntProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._set;
        int[] iArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tFloatIntProcedure.execute(fArr[i], iArr[i])) {
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

    public int get(float f) {
        int iIndex = index(f);
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

    public boolean increment(float f) {
        return adjustValue(f, 1);
    }

    public TFloatIntIterator iterator() {
        return new TFloatIntIterator(this);
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

    public int put(float f, int i) {
        int i2;
        boolean z;
        int iInsertionIndex = insertionIndex(f);
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
        this._set[iInsertionIndex] = f;
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
        float[] fArr = this._set;
        int[] iArr = this._values;
        byte[] bArr = this._states;
        this._set = new float[i];
        this._values = new int[i];
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
                this._values[iInsertionIndex] = iArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public int remove(float f) {
        int iIndex = index(f);
        if (iIndex < 0) {
            return 0;
        }
        int i = this._values[iIndex];
        removeAt(iIndex);
        return i;
    }

    @Override // gnu.trove.TFloatHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TFloatIntProcedure tFloatIntProcedure) {
        byte[] bArr = this._states;
        float[] fArr = this._set;
        int[] iArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tFloatIntProcedure.execute(fArr[length], iArr[length])) {
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
        this._values = i == -1 ? null : new int[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TFloatIntProcedure() { // from class: gnu.trove.TFloatIntHashMap.1
            @Override // gnu.trove.TFloatIntProcedure
            public boolean execute(float f, int i) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(f);
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

    public TFloatIntHashMap(int i) {
        super(i);
    }

    public TFloatIntHashMap(int i, float f) {
        super(i, f);
    }

    public TFloatIntHashMap(TFloatHashingStrategy tFloatHashingStrategy) {
        super(tFloatHashingStrategy);
    }

    public TFloatIntHashMap(int i, TFloatHashingStrategy tFloatHashingStrategy) {
        super(i, tFloatHashingStrategy);
    }

    public TFloatIntHashMap(int i, float f, TFloatHashingStrategy tFloatHashingStrategy) {
        super(i, f, tFloatHashingStrategy);
    }
}
