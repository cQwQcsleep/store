package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteLongHashMap extends TByteHash {
    protected transient long[] _values;

    public static final class EqProcedure implements TByteLongProcedure {
        private final TByteLongHashMap _otherMap;

        public EqProcedure(TByteLongHashMap tByteLongHashMap) {
            this._otherMap = tByteLongHashMap;
        }

        private static boolean eq(long j, long j2) {
            return j == j2;
        }

        @Override // gnu.trove.TByteLongProcedure
        public final boolean execute(byte b, long j) {
            return this._otherMap.index(b) >= 0 && eq(j, this._otherMap.get(b));
        }
    }

    public final class HashProcedure implements TByteLongProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TByteLongProcedure
        public final boolean execute(byte b, long j) {
            this.h += TByteLongHashMap.this._hashingStrategy.computeHashCode(b) ^ HashFunctions.hash(j);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TByteLongHashMap() {
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
            put(objectInputStream.readByte(), objectInputStream.readLong());
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

    public boolean adjustValue(byte b, long j) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return false;
        }
        long[] jArr = this._values;
        jArr[iIndex] = jArr[iIndex] + j;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        byte[] bArr = this._set;
        long[] jArr = this._values;
        if (jArr == null) {
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
            jArr[i] = 0;
            bArr2[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TByteLongHashMap tByteLongHashMap = (TByteLongHashMap) super.clone();
        long[] jArr = this._values;
        tByteLongHashMap._values = jArr == null ? null : (long[]) jArr.clone();
        return tByteLongHashMap;
    }

    public boolean containsKey(byte b) {
        return contains(b);
    }

    public boolean containsValue(long j) {
        byte[] bArr = this._states;
        long[] jArr = this._values;
        if (bArr == null) {
            return false;
        }
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            if (bArr[i] == 1 && j == jArr[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TByteLongHashMap)) {
            return false;
        }
        TByteLongHashMap tByteLongHashMap = (TByteLongHashMap) obj;
        if (tByteLongHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tByteLongHashMap));
    }

    public boolean forEachEntry(TByteLongProcedure tByteLongProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        long[] jArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tByteLongProcedure.execute(bArr2[i], jArr[i])) {
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

    public boolean forEachValue(TLongProcedure tLongProcedure) {
        byte[] bArr = this._states;
        long[] jArr = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tLongProcedure.execute(jArr[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public long get(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return 0L;
        }
        return this._values[iIndex];
    }

    public long[] getValues() {
        long[] jArr = new long[size()];
        long[] jArr2 = this._values;
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
                    jArr[i] = jArr2[i2];
                    i++;
                }
                length = i2;
            }
        }
        return jArr;
    }

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEachEntry(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public boolean increment(byte b) {
        return adjustValue(b, 1L);
    }

    public TByteLongIterator iterator() {
        return new TByteLongIterator(this);
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

    public long put(byte b, long j) {
        long j2;
        boolean z;
        int iInsertionIndex = insertionIndex(b);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            j2 = this._values[iInsertionIndex];
            z = false;
        } else {
            j2 = 0;
            z = true;
        }
        byte[] bArr = this._states;
        byte b2 = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = b;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = j;
        if (z) {
            postInsertHook(b2 == 0);
        }
        return j2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        byte[] bArr = this._set;
        long[] jArr = this._values;
        byte[] bArr2 = this._states;
        this._set = new byte[i];
        this._values = new long[i];
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
                this._values[iInsertionIndex] = jArr[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public long remove(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return 0L;
        }
        long j = this._values[iIndex];
        removeAt(iIndex);
        return j;
    }

    @Override // gnu.trove.TByteHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TByteLongProcedure tByteLongProcedure) {
        byte[] bArr = this._states;
        byte[] bArr2 = this._set;
        long[] jArr = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tByteLongProcedure.execute(bArr2[length], jArr[length])) {
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
        this._values = i == -1 ? null : new long[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TByteLongProcedure() { // from class: gnu.trove.TByteLongHashMap.1
            @Override // gnu.trove.TByteLongProcedure
            public boolean execute(byte b, long j) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append((int) b);
                sb.append('=');
                sb.append(j);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TLongFunction tLongFunction) {
        byte[] bArr = this._states;
        long[] jArr = this._values;
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
                jArr[i] = tLongFunction.execute(jArr[i]);
            }
            length = i;
        }
    }

    public TByteLongHashMap(int i) {
        super(i);
    }

    public TByteLongHashMap(int i, float f) {
        super(i, f);
    }

    public TByteLongHashMap(TByteHashingStrategy tByteHashingStrategy) {
        super(tByteHashingStrategy);
    }

    public TByteLongHashMap(int i, TByteHashingStrategy tByteHashingStrategy) {
        super(i, tByteHashingStrategy);
    }

    public TByteLongHashMap(int i, float f, TByteHashingStrategy tByteHashingStrategy) {
        super(i, f, tByteHashingStrategy);
    }
}
