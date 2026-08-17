package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TObjectLongHashMap<K> extends TObjectHash<K> {
    protected transient long[] _values;

    public static final class EqProcedure<K> implements TObjectLongProcedure<K> {
        private final TObjectLongHashMap<K> _otherMap;

        public EqProcedure(TObjectLongHashMap<K> tObjectLongHashMap) {
            this._otherMap = tObjectLongHashMap;
        }

        private static boolean eq(long j, long j2) {
            return j == j2;
        }

        @Override // gnu.trove.TObjectLongProcedure
        public final boolean execute(K k, long j) {
            return this._otherMap.index(k) >= 0 && eq(j, this._otherMap.get(k));
        }
    }

    public final class HashProcedure implements TObjectLongProcedure<K> {
        private int h;

        public HashProcedure() {
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // gnu.trove.TObjectLongProcedure
        public boolean execute(K k, long j) {
            this.h += TObjectLongHashMap.this._hashingStrategy.computeHashCode((T) k) ^ HashFunctions.hash(j);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TObjectLongHashMap() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        setUp(i);
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            put(objectInputStream.readObject(), objectInputStream.readLong());
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

    public boolean adjustValue(K k, long j) {
        int iIndex = index(k);
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
        Object[] objArr = this._set;
        long[] jArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            objArr[i] = null;
            jArr[i] = 0;
            length = i;
        }
    }

    public boolean containsKey(K k) {
        return contains(k);
    }

    public boolean containsValue(long j) {
        Object[] objArr = this._set;
        long[] jArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && j == jArr[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TObjectLongHashMap)) {
            return false;
        }
        TObjectLongHashMap tObjectLongHashMap = (TObjectLongHashMap) obj;
        if (tObjectLongHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tObjectLongHashMap));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachEntry(TObjectLongProcedure<K> tObjectLongProcedure) {
        Object[] objArr = this._set;
        long[] jArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && !tObjectLongProcedure.execute(obj, jArr[i])) {
                return false;
            }
            length = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachKey(TObjectProcedure<K> tObjectProcedure) {
        return forEach(tObjectProcedure);
    }

    public boolean forEachValue(TLongProcedure tLongProcedure) {
        Object[] objArr = this._set;
        long[] jArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && !tLongProcedure.execute(jArr[i])) {
                return false;
            }
            length = i;
        }
    }

    public long get(K k) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return 0L;
        }
        return this._values[iIndex];
    }

    public long[] getValues() {
        long[] jArr = new long[size()];
        long[] jArr2 = this._values;
        Object[] objArr = this._set;
        int length = objArr.length;
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return jArr;
            }
            Object obj = objArr[i2];
            if (obj != null && obj != TObjectHash.REMOVED) {
                jArr[i] = jArr2[i2];
                i++;
            }
            length = i2;
        }
    }

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEachEntry(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public boolean increment(K k) {
        return adjustValue(k, 1L);
    }

    public TObjectLongIterator<K> iterator() {
        return new TObjectLongIterator<>(this);
    }

    public Object[] keys() {
        Object[] objArr = new Object[size()];
        Object[] objArr2 = this._set;
        int length = objArr2.length;
        int i = 0;
        while (true) {
            length--;
            if (length <= 0) {
                return objArr;
            }
            Object obj = objArr2[length];
            if (obj != null && obj != TObjectHash.REMOVED) {
                objArr[i] = obj;
                i++;
            }
        }
    }

    public long put(K k, long j) {
        long j2;
        boolean z;
        int iInsertionIndex = insertionIndex(k);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            j2 = this._values[iInsertionIndex];
            z = false;
        } else {
            j2 = 0;
            z = true;
        }
        Object[] objArr = this._set;
        Object obj = objArr[iInsertionIndex];
        objArr[iInsertionIndex] = k;
        this._values[iInsertionIndex] = j;
        if (z) {
            postInsertHook(obj == null);
        }
        return j2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        Object[] objArr = this._set;
        long[] jArr = this._values;
        this._set = new Object[i];
        this._values = new long[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            Object obj = objArr[i2];
            if (obj != null && obj != TObjectHash.REMOVED) {
                int iInsertionIndex = insertionIndex(obj);
                if (iInsertionIndex < 0) {
                    throwObjectContractViolation(this._set[(-iInsertionIndex) - 1], obj);
                }
                this._set[iInsertionIndex] = obj;
                this._values[iInsertionIndex] = jArr[i2];
            }
            iCapacity = i2;
        }
    }

    public long remove(K k) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return 0L;
        }
        long j = this._values[iIndex];
        removeAt(iIndex);
        return j;
    }

    @Override // gnu.trove.TObjectHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0;
        super.removeAt(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean retainEntries(TObjectLongProcedure<K> tObjectLongProcedure) {
        Object[] objArr = this._set;
        long[] jArr = this._values;
        stopCompactingOnRemove();
        boolean z = false;
        try {
            int length = objArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    startCompactingOnRemove(z);
                    return z;
                }
                Object obj = objArr[i];
                if (obj != null && obj != TObjectHash.REMOVED && !tObjectLongProcedure.execute(obj, jArr[i])) {
                    removeAt(i);
                    z = true;
                }
                length = i;
            }
        } catch (Throwable th) {
            startCompactingOnRemove(z);
            throw th;
        }
    }

    @Override // gnu.trove.TObjectHash, gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? null : new long[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TObjectLongProcedure<K>() { // from class: gnu.trove.TObjectLongHashMap.1
            @Override // gnu.trove.TObjectLongProcedure
            public boolean execute(K k, long j) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                StringBuilder sb3 = sb;
                if (k == this) {
                    k = (K) "(this Map)";
                }
                sb3.append(k);
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
        Object[] objArr = this._set;
        long[] jArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED) {
                jArr[i] = tLongFunction.execute(jArr[i]);
            }
            length = i;
        }
    }

    public TObjectLongHashMap(int i) {
        super(i);
    }

    public TObjectLongHashMap(int i, float f) {
        super(i, f);
    }

    public TObjectLongHashMap(TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(tObjectHashingStrategy);
    }

    public TObjectLongHashMap(int i, TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(i, tObjectHashingStrategy);
    }

    public TObjectLongHashMap(int i, float f, TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(i, f, tObjectHashingStrategy);
    }
}
