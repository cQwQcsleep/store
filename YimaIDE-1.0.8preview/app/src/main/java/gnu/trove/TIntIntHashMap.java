package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TIntIntHashMap extends TIntHash {
    protected transient int[] _values;

    public static final class EqProcedure implements TIntIntProcedure {
        private final TIntIntHashMap _otherMap;

        public EqProcedure(TIntIntHashMap tIntIntHashMap) {
            this._otherMap = tIntIntHashMap;
        }

        private static boolean eq(int i, int i2) {
            return i == i2;
        }

        @Override // gnu.trove.TIntIntProcedure
        public final boolean execute(int i, int i2) {
            return this._otherMap.index(i) >= 0 && eq(i2, this._otherMap.get(i));
        }
    }

    public final class HashProcedure implements TIntIntProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TIntIntProcedure
        public final boolean execute(int i, int i2) {
            this.h += TIntIntHashMap.this._hashingStrategy.computeHashCode(i) ^ HashFunctions.hash(i2);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TIntIntHashMap() {
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
            put(objectInputStream.readInt(), objectInputStream.readInt());
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

    public boolean adjustValue(int i, int i2) {
        int iIndex = index(i);
        if (iIndex < 0) {
            return false;
        }
        int[] iArr = this._values;
        iArr[iIndex] = iArr[iIndex] + i2;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        int[] iArr = this._set;
        int[] iArr2 = this._values;
        if (iArr2 == null) {
            return;
        }
        byte[] bArr = this._states;
        int length = iArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            iArr[i] = 0;
            iArr2[i] = 0;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TIntHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TIntIntHashMap tIntIntHashMap = (TIntIntHashMap) super.clone();
        int[] iArr = this._values;
        tIntIntHashMap._values = iArr == null ? null : (int[]) iArr.clone();
        return tIntIntHashMap;
    }

    public boolean containsKey(int i) {
        return contains(i);
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
        if (!(obj instanceof TIntIntHashMap)) {
            return false;
        }
        TIntIntHashMap tIntIntHashMap = (TIntIntHashMap) obj;
        if (tIntIntHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tIntIntHashMap));
    }

    public boolean forEachEntry(TIntIntProcedure tIntIntProcedure) {
        byte[] bArr = this._states;
        int[] iArr = this._set;
        int[] iArr2 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tIntIntProcedure.execute(iArr[i], iArr2[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public boolean forEachKey(TIntProcedure tIntProcedure) {
        return forEach(tIntProcedure);
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

    public int get(int i) {
        int iIndex = index(i);
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

    public boolean increment(int i) {
        return adjustValue(i, 1);
    }

    public TIntIntIterator iterator() {
        return new TIntIntIterator(this);
    }

    public int[] keys() {
        int[] iArr = new int[size()];
        int[] iArr2 = this._set;
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

    public int put(int i, int i2) {
        int i3;
        boolean z;
        int iInsertionIndex = insertionIndex(i);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            i3 = this._values[iInsertionIndex];
            z = false;
        } else {
            i3 = 0;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = i;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = i2;
        if (z) {
            postInsertHook(b == 0);
        }
        return i3;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        int[] iArr = this._set;
        int[] iArr2 = this._values;
        byte[] bArr = this._states;
        this._set = new int[i];
        this._values = new int[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr[i2] == 1) {
                int i3 = iArr[i2];
                int iInsertionIndex = insertionIndex(i3);
                this._set[iInsertionIndex] = i3;
                this._values[iInsertionIndex] = iArr2[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public int remove(int i) {
        int iIndex = index(i);
        if (iIndex < 0) {
            return 0;
        }
        int i2 = this._values[iIndex];
        removeAt(iIndex);
        return i2;
    }

    @Override // gnu.trove.TIntHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TIntIntProcedure tIntIntProcedure) {
        byte[] bArr = this._states;
        int[] iArr = this._set;
        int[] iArr2 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tIntIntProcedure.execute(iArr[length], iArr2[length])) {
                    removeAt(length);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // gnu.trove.TIntHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? null : new int[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TIntIntProcedure() { // from class: gnu.trove.TIntIntHashMap.1
            @Override // gnu.trove.TIntIntProcedure
            public boolean execute(int i, int i2) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(i);
                sb.append('=');
                sb.append(i2);
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

    public TIntIntHashMap(int i) {
        super(i);
    }

    public TIntIntHashMap(int i, float f) {
        super(i, f);
    }

    public TIntIntHashMap(TIntHashingStrategy tIntHashingStrategy) {
        super(tIntHashingStrategy);
    }

    public TIntIntHashMap(int i, TIntHashingStrategy tIntHashingStrategy) {
        super(i, tIntHashingStrategy);
    }

    public TIntIntHashMap(int i, float f, TIntHashingStrategy tIntHashingStrategy) {
        super(i, f, tIntHashingStrategy);
    }
}
