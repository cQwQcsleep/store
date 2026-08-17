package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteByteHashMap extends TByteHash {
    protected transient byte[] _values;

    public static final class EqProcedure implements TByteByteProcedure {
        private final TByteByteHashMap _otherMap;

        public EqProcedure(TByteByteHashMap tByteByteHashMap) {
            this._otherMap = tByteByteHashMap;
        }

        private static boolean eq(byte b, byte b2) {
            return b == b2;
        }

        @Override // gnu.trove.TByteByteProcedure
        public final boolean execute(byte b, byte b2) {
            return this._otherMap.index(b) >= 0 && eq(b2, this._otherMap.get(b));
        }
    }

    public final class HashProcedure implements TByteByteProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TByteByteProcedure
        public final boolean execute(byte b, byte b2) {
            this.h += TByteByteHashMap.this._hashingStrategy.computeHashCode(b) ^ HashFunctions.hash((int) b2);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TByteByteHashMap() {
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
            put(objectInputStream.readByte(), objectInputStream.readByte());
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

    public boolean adjustValue(byte b, byte b2) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return false;
        }
        byte[] bArr = this._values;
        bArr[iIndex] = (byte) (bArr[iIndex] + b2);
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        byte[] bArr = this._set;
        byte[] bArr2 = this._values;
        if (bArr2 == null) {
            return;
        }
        byte[] bArr3 = this._states;
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            bArr[i] = 0;
            bArr2[i] = 0;
            bArr3[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TByteByteHashMap tByteByteHashMap = (TByteByteHashMap) super.clone();
        byte[] bArr = this._values;
        tByteByteHashMap._values = bArr == null ? null : (byte[]) bArr.clone();
        return tByteByteHashMap;
    }

    public boolean containsKey(byte b) {
        return contains(b);
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
        if (!(obj instanceof TByteByteHashMap)) {
            return false;
        }
        TByteByteHashMap tByteByteHashMap = (TByteByteHashMap) obj;
        if (tByteByteHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tByteByteHashMap));
    }

    public boolean forEachEntry(TByteByteProcedure tByteByteProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        byte[] bArr3 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tByteByteProcedure.execute(bArr2[i], bArr3[i])) {
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

    public byte get(byte b) {
        int iIndex = index(b);
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

    public boolean increment(byte b) {
        return adjustValue(b, (byte) 1);
    }

    public TByteByteIterator iterator() {
        return new TByteByteIterator(this);
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

    public byte put(byte b, byte b2) {
        byte b3;
        boolean z;
        int iInsertionIndex = insertionIndex(b);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            b3 = this._values[iInsertionIndex];
            z = false;
        } else {
            b3 = 0;
            z = true;
        }
        byte[] bArr = this._states;
        byte b4 = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = b;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = b2;
        if (z) {
            postInsertHook(b4 == 0);
        }
        return b3;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        byte[] bArr = this._set;
        byte[] bArr2 = this._values;
        byte[] bArr3 = this._states;
        this._set = new byte[i];
        this._values = new byte[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr3[i2] == 1) {
                byte b = bArr[i2];
                int iInsertionIndex = insertionIndex(b);
                this._set[iInsertionIndex] = b;
                this._values[iInsertionIndex] = bArr2[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public byte remove(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return (byte) 0;
        }
        byte b2 = this._values[iIndex];
        removeAt(iIndex);
        return b2;
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TByteByteProcedure tByteByteProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        byte[] bArr3 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tByteByteProcedure.execute(bArr2[length], bArr3[length])) {
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
        this._values = i == -1 ? null : new byte[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TByteByteProcedure() { // from class: gnu.trove.TByteByteHashMap.1
            @Override // gnu.trove.TByteByteProcedure
            public boolean execute(byte b, byte b2) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append((int) b);
                sb.append('=');
                sb.append((int) b2);
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

    public TByteByteHashMap(int i) {
        super(i);
    }

    public TByteByteHashMap(int i, float f) {
        super(i, f);
    }

    public TByteByteHashMap(TByteHashingStrategy tByteHashingStrategy) {
        super(tByteHashingStrategy);
    }

    public TByteByteHashMap(int i, TByteHashingStrategy tByteHashingStrategy) {
        super(i, tByteHashingStrategy);
    }

    public TByteByteHashMap(int i, float f, TByteHashingStrategy tByteHashingStrategy) {
        super(i, f, tByteHashingStrategy);
    }
}
