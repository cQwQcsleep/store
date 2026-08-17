package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TLongLongHashMap extends TLongHash {
    protected transient long[] _values;

    public static final class EqProcedure implements TLongLongProcedure {
        private final TLongLongHashMap _otherMap;

        public EqProcedure(TLongLongHashMap tLongLongHashMap) {
            this._otherMap = tLongLongHashMap;
        }

        private static boolean eq(long j, long j2) {
            return j == j2;
        }

        @Override // gnu.trove.TLongLongProcedure
        public final boolean execute(long j, long j2) {
            return this._otherMap.index(j) >= 0 && eq(j2, this._otherMap.get(j));
        }
    }

    public final class HashProcedure implements TLongLongProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TLongLongProcedure
        public final boolean execute(long j, long j2) {
            this.h += TLongLongHashMap.this._hashingStrategy.computeHashCode(j) ^ HashFunctions.hash(j2);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TLongLongHashMap() {
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
            put(objectInputStream.readLong(), objectInputStream.readLong());
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

    public boolean adjustValue(long j, long j2) {
        int iIndex = index(j);
        if (iIndex < 0) {
            return false;
        }
        long[] jArr = this._values;
        jArr[iIndex] = jArr[iIndex] + j2;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        long[] jArr = this._set;
        long[] jArr2 = this._values;
        if (jArr2 == null) {
            return;
        }
        byte[] bArr = this._states;
        int length = jArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            jArr[i] = 0;
            jArr2[i] = 0;
            bArr[i] = 0;
            length = i;
        }
    }

    @Override // gnu.trove.TLongHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public Object clone() {
        TLongLongHashMap tLongLongHashMap = (TLongLongHashMap) super.clone();
        long[] jArr = this._values;
        tLongLongHashMap._values = jArr == null ? null : (long[]) jArr.clone();
        return tLongLongHashMap;
    }

    public boolean containsKey(long j) {
        return contains(j);
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
        if (!(obj instanceof TLongLongHashMap)) {
            return false;
        }
        TLongLongHashMap tLongLongHashMap = (TLongLongHashMap) obj;
        if (tLongLongHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tLongLongHashMap));
    }

    public boolean forEachEntry(TLongLongProcedure tLongLongProcedure) {
        byte[] bArr = this._states;
        long[] jArr = this._set;
        long[] jArr2 = this._values;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    break;
                }
                if (bArr[i] == 1 && !tLongLongProcedure.execute(jArr[i], jArr2[i])) {
                    return false;
                }
                length = i;
            }
        }
        return true;
    }

    public boolean forEachKey(TLongProcedure tLongProcedure) {
        return forEach(tLongProcedure);
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

    public long get(long j) {
        int iIndex = index(j);
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

    public boolean increment(long j) {
        return adjustValue(j, 1L);
    }

    public TLongLongIterator iterator() {
        return new TLongLongIterator(this);
    }

    public long[] keys() {
        long[] jArr = new long[size()];
        long[] jArr2 = this._set;
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

    public long put(long j, long j2) {
        long j3;
        boolean z;
        int iInsertionIndex = insertionIndex(j);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            j3 = this._values[iInsertionIndex];
            z = false;
        } else {
            j3 = 0;
            z = true;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = j;
        bArr[iInsertionIndex] = 1;
        this._values[iInsertionIndex] = j2;
        if (z) {
            postInsertHook(b == 0);
        }
        return j3;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        long[] jArr = this._set;
        long[] jArr2 = this._values;
        byte[] bArr = this._states;
        this._set = new long[i];
        this._values = new long[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr[i2] == 1) {
                long j = jArr[i2];
                int iInsertionIndex = insertionIndex(j);
                this._set[iInsertionIndex] = j;
                this._values[iInsertionIndex] = jArr2[i2];
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public long remove(long j) {
        int iIndex = index(j);
        if (iIndex < 0) {
            return 0L;
        }
        long j2 = this._values[iIndex];
        removeAt(iIndex);
        return j2;
    }

    @Override // gnu.trove.TLongHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    public boolean retainEntries(TLongLongProcedure tLongLongProcedure) {
        byte[] bArr = this._states;
        long[] jArr = this._set;
        long[] jArr2 = this._values;
        boolean z = false;
        if (bArr != null) {
            int length = bArr.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && !tLongLongProcedure.execute(jArr[length], jArr2[length])) {
                    removeAt(length);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // gnu.trove.TLongHash, gnu.trove.TPrimitiveHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? null : new long[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TLongLongProcedure() { // from class: gnu.trove.TLongLongHashMap.1
            @Override // gnu.trove.TLongLongProcedure
            public boolean execute(long j, long j2) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(j);
                sb.append('=');
                sb.append(j2);
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

    public TLongLongHashMap(int i) {
        super(i);
    }

    public TLongLongHashMap(int i, float f) {
        super(i, f);
    }

    public TLongLongHashMap(TLongHashingStrategy tLongHashingStrategy) {
        super(tLongHashingStrategy);
    }

    public TLongLongHashMap(int i, TLongHashingStrategy tLongHashingStrategy) {
        super(i, tLongHashingStrategy);
    }

    public TLongLongHashMap(int i, float f, TLongHashingStrategy tLongHashingStrategy) {
        super(i, f, tLongHashingStrategy);
    }
}
