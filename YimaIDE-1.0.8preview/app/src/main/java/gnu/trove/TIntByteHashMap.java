package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TIntByteHashMap extends TIntHash {
    protected transient byte[] _values;

    public static final class EqProcedure implements TIntByteProcedure {
        private final TIntByteHashMap _otherMap;

        public EqProcedure(TIntByteHashMap tIntByteHashMap) {
            this._otherMap = tIntByteHashMap;
        }

        private static boolean eq(byte b, byte b2) {
            return b == b2;
        }

        @Override // gnu.trove.TIntByteProcedure
        public final boolean execute(int i, byte b) {
            return this._otherMap.index(i) >= 0 && eq(b, this._otherMap.get(i));
        }
    }

    public final class HashProcedure implements TIntByteProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TIntByteProcedure
        public final boolean execute(int i, byte b) {
            this.h += TIntByteHashMap.this._hashingStrategy.computeHashCode(i) ^ HashFunctions.hash((int) b);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TIntByteHashMap() {
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
            put(objectInputStream.readInt(), objectInputStream.readByte());
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

    public boolean adjustValue(int i, byte b) {
        int iIndex = index(i);
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
        int[] iArr = this._set;
        byte[] bArr = this._values;
        if (bArr == null) {
            return;
        }
        byte[] bArr2 = this._states;
        int length = iArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            iArr[i] = 0;
            bArr[i] = 0;
            bArr2[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TIntHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TIntByteHashMap tIntByteHashMap = (TIntByteHashMap) super.clone();
        byte[] bArr = this._values;
        tIntByteHashMap._values = bArr == null ? null : (byte[]) bArr.clone();
        return tIntByteHashMap;
    }

    public boolean containsKey(int i) {
        return contains(i);
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
        if (!(obj instanceof TIntByteHashMap)) {
            return false;
        }
        TIntByteHashMap tIntByteHashMap = (TIntByteHashMap) obj;
        if (tIntByteHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tIntByteHashMap));
    }

    public boolean forEachEntry(TIntByteProcedure tIntByteProcedure) {
        byte[] bArr = this._states;
        int[] iArr = this._set;
        byte[] bArr2 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tIntByteProcedure.execute(iArr[i], bArr2[i])) {
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

    public byte get(int i) {
        int iIndex = index(i);
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

    public boolean increment(int i) {
        return adjustValue(i, (byte) 1);
    }

    public TIntByteIterator iterator() {
        return new TIntByteIterator(this);
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

    public byte put(int i, byte b) {
        byte b2;
        boolean z;
        int iInsertionIndex = insertionIndex(i);
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
        this._set[iInsertionIndex] = i;
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
        int[] iArr = this._set;
        byte[] bArr = this._values;
        byte[] bArr2 = this._states;
        this._set = new int[i];
        this._values = new byte[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr2[i2] == 1) {
                int i3 = iArr[i2];
                int iInsertionIndex = insertionIndex(i3);
                this._set[iInsertionIndex] = i3;
                this._values[iInsertionIndex] = bArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public byte remove(int i) {
        int iIndex = index(i);
        if (iIndex < 0) {
            return (byte) 0;
        }
        byte b = this._values[iIndex];
        removeAt(iIndex);
        return b;
    }

    @Override // gnu.trove.TIntHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TIntByteProcedure tIntByteProcedure) {
        byte[] bArr = this._states;
        int[] iArr = this._set;
        byte[] bArr2 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tIntByteProcedure.execute(iArr[length], bArr2[length])) {
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
        this._values = i == -1 ? null : new byte[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TIntByteProcedure() { // from class: gnu.trove.TIntByteHashMap.1
            @Override // gnu.trove.TIntByteProcedure
            public boolean execute(int i, byte b) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(i);
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

    public TIntByteHashMap(int i) {
        super(i);
    }

    public TIntByteHashMap(int i, float f) {
        super(i, f);
    }

    public TIntByteHashMap(TIntHashingStrategy tIntHashingStrategy) {
        super(tIntHashingStrategy);
    }

    public TIntByteHashMap(int i, TIntHashingStrategy tIntHashingStrategy) {
        super(i, tIntHashingStrategy);
    }

    public TIntByteHashMap(int i, float f, TIntHashingStrategy tIntHashingStrategy) {
        super(i, f, tIntHashingStrategy);
    }
}
