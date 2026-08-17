package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TLongObjectHashMap<V> extends THash implements TLongHashingStrategy {
    protected final TLongHashingStrategy _hashingStrategy;
    protected transient long[] _set;
    protected transient V[] _values;

    public static final class EqProcedure<V> implements TLongObjectProcedure<V> {
        private final TLongObjectHashMap<V> _otherMap;

        public EqProcedure(TLongObjectHashMap<V> tLongObjectHashMap) {
            this._otherMap = tLongObjectHashMap;
        }

        private static boolean eq(Object obj, Object obj2) {
            if (obj != obj2) {
                return obj != null && obj.equals(obj2);
            }
            return true;
        }

        @Override // gnu.trove.TLongObjectProcedure
        public final boolean execute(long j, V v) {
            return this._otherMap.index(j) >= 0 && eq(v, this._otherMap.get(j));
        }
    }

    public final class HashProcedure implements TLongObjectProcedure<V> {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TLongObjectProcedure
        public final boolean execute(long j, V v) {
            this.h += TLongObjectHashMap.this._hashingStrategy.computeHashCode(j) ^ HashFunctions.hash(v);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TLongObjectHashMap() {
        this._hashingStrategy = this;
    }

    private static boolean isFree(Object[] objArr, int i) {
        return objArr[i] == null;
    }

    public static boolean isFull(Object[] objArr, int i) {
        Object obj = objArr[i];
        return (obj == null || obj == TObjectHash.REMOVED) ? false : true;
    }

    private static boolean isRemoved(Object[] objArr, int i) {
        return objArr[i] == TObjectHash.REMOVED;
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
            put(objectInputStream.readLong(), objectInputStream.readObject());
            i = i2;
        }
    }

    private static <V> V unwrapNull(V v) {
        if (v == TObjectHash.NULL) {
            return null;
        }
        return v;
    }

    private static <V> V wrapNull(V v) {
        return v == null ? (V) TObjectHash.NULL : v;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this._size);
        SerializationProcedure serializationProcedure = new SerializationProcedure(objectOutputStream);
        if (!forEachEntry(serializationProcedure)) {
            throw serializationProcedure.exception;
        }
    }

    @Override // gnu.trove.THash
    public int capacity() {
        return this._values.length;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        long[] jArr = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            jArr[i] = 0;
            vArr[i] = null;
            length = i;
        }
    }

    @Override // gnu.trove.THash
    public TLongObjectHashMap<V> clone() {
        TLongObjectHashMap<V> tLongObjectHashMap = (TLongObjectHashMap) super.clone();
        V[] vArr = this._values;
        Object[] objArr = THash.EMPTY_OBJECT_ARRAY;
        tLongObjectHashMap._values = vArr == objArr ? (V[]) objArr : (V[]) ((Object[]) vArr.clone());
        tLongObjectHashMap._set = this._values == objArr ? null : (long[]) this._set.clone();
        return tLongObjectHashMap;
    }

    @Override // gnu.trove.TLongHashingStrategy
    public final int computeHashCode(long j) {
        return HashFunctions.hash(j);
    }

    public boolean contains(long j) {
        return index(j) >= 0;
    }

    public boolean containsKey(long j) {
        return contains(j);
    }

    public boolean containsValue(V v) {
        V[] vArr = this._values;
        if (v != null) {
            int length = vArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    return false;
                }
                Object objUnwrapNull = unwrapNull(vArr[i]);
                if (isFull(vArr, i) && (v == objUnwrapNull || v.equals(objUnwrapNull))) {
                    break;
                }
                length = i;
            }
            return true;
        }
        int length2 = vArr.length;
        while (true) {
            int i2 = length2 - 1;
            if (length2 <= 0) {
                return false;
            }
            if (TObjectHash.NULL == vArr[i2]) {
                return true;
            }
            length2 = i2;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TLongObjectHashMap)) {
            return false;
        }
        TLongObjectHashMap tLongObjectHashMap = (TLongObjectHashMap) obj;
        if (tLongObjectHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tLongObjectHashMap));
    }

    public boolean forEach(TLongProcedure tLongProcedure) {
        long[] jArr = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (isFull(vArr, i) && !tLongProcedure.execute(jArr[i])) {
                return false;
            }
            length = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachEntry(TLongObjectProcedure<V> tLongObjectProcedure) {
        long[] jArr = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (isFull(vArr, i) && !tLongObjectProcedure.execute(jArr[i], unwrapNull(vArr[i]))) {
                return false;
            }
            length = i;
        }
    }

    public boolean forEachKey(TLongProcedure tLongProcedure) {
        return forEach(tLongProcedure);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachValue(TObjectProcedure<V> tObjectProcedure) {
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (isFull(vArr, i) && !tObjectProcedure.execute(unwrapNull(vArr[i]))) {
                return false;
            }
            length = i;
        }
    }

    public V get(long j) {
        int iIndex = index(j);
        if (iIndex < 0) {
            return null;
        }
        return (V) unwrapNull(this._values[iIndex]);
    }

    public Object[] getValues() {
        Object[] objArr = new Object[size()];
        V[] vArr = this._values;
        int length = vArr.length;
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return objArr;
            }
            if (isFull(vArr, i2)) {
                objArr[i] = unwrapNull(vArr[i2]);
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

    public int index(long j) {
        long[] jArr = this._set;
        V[] vArr = this._values;
        if (vArr == THash.EMPTY_OBJECT_ARRAY) {
            return -1;
        }
        int length = jArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(j) & Integer.MAX_VALUE;
        int i = iComputeHashCode % length;
        if (!isFree(vArr, i) && (isRemoved(vArr, i) || jArr[i] != j)) {
            int i2 = (iComputeHashCode % (length - 2)) + 1;
            while (true) {
                i -= i2;
                if (i < 0) {
                    i += length;
                }
                if (isFree(vArr, i) || (!isRemoved(vArr, i) && jArr[i] == j)) {
                    break;
                }
            }
        }
        if (isFree(vArr, i)) {
            return -1;
        }
        return i;
    }

    public int insertionIndex(long j) {
        if (this._values == THash.EMPTY_OBJECT_ARRAY) {
            setUp(6);
        }
        V[] vArr = this._values;
        long[] jArr = this._set;
        int length = jArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(j) & Integer.MAX_VALUE;
        int i = iComputeHashCode % length;
        if (isFree(vArr, i)) {
            return i;
        }
        if (!isFull(vArr, i) || jArr[i] != j) {
            int i2 = (iComputeHashCode % (length - 2)) + 1;
            int i3 = isRemoved(vArr, i) ? i : -1;
            do {
                i -= i2;
                if (i < 0) {
                    i += length;
                }
                if (i3 == -1 && isRemoved(vArr, i)) {
                    i3 = i;
                }
                if (!isFull(vArr, i)) {
                    break;
                }
            } while (jArr[i] != j);
            if (isRemoved(vArr, i)) {
                while (!isFree(vArr, i) && (isRemoved(vArr, i) || jArr[i] != j)) {
                    i -= i2;
                    if (i < 0) {
                        i += length;
                    }
                }
            }
            if (!isFull(vArr, i)) {
                return i3 == -1 ? i : i3;
            }
        }
        return (-i) - 1;
    }

    public TLongObjectIterator<V> iterator() {
        return new TLongObjectIterator<>(this);
    }

    public long[] keys() {
        long[] jArr = new long[size()];
        long[] jArr2 = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return jArr;
            }
            if (isFull(vArr, i2)) {
                jArr[i] = jArr2[i2];
                i++;
            }
            length = i2;
        }
    }

    public V put(long j, V v) {
        boolean zIsFree;
        V v2;
        int iInsertionIndex = insertionIndex(j);
        V[] vArr = this._values;
        boolean z = true;
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            v2 = (V) unwrapNull(vArr[iInsertionIndex]);
            z = false;
            zIsFree = false;
        } else {
            zIsFree = isFree(vArr, iInsertionIndex);
            v2 = null;
        }
        this._set[iInsertionIndex] = j;
        ((V[]) this._values)[iInsertionIndex] = wrapNull(v);
        if (z) {
            postInsertHook(zIsFree);
        }
        return v2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        long[] jArr = this._set;
        V[] vArr = this._values;
        this._set = new long[i];
        this._values = (V[]) new Object[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (isFull(vArr, i2)) {
                long j = jArr[i2];
                int iInsertionIndex = insertionIndex(j);
                this._set[iInsertionIndex] = j;
                this._values[iInsertionIndex] = vArr[i2];
            }
            iCapacity = i2;
        }
    }

    public V remove(long j) {
        int iIndex = index(j);
        if (iIndex < 0) {
            return null;
        }
        V v = (V) unwrapNull(this._values[iIndex]);
        removeAt(iIndex);
        return v;
    }

    @Override // gnu.trove.THash
    public void removeAt(int i) {
        ((V[]) this._values)[i] = TObjectHash.REMOVED;
        super.removeAt(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean retainEntries(TLongObjectProcedure<V> tLongObjectProcedure) {
        long[] jArr = this._set;
        V[] vArr = this._values;
        stopCompactingOnRemove();
        boolean z = false;
        try {
            int length = vArr.length;
            while (true) {
                int i = length - 1;
                if (length <= 0) {
                    startCompactingOnRemove(z);
                    return z;
                }
                if (isFull(vArr, i) && !tLongObjectProcedure.execute(jArr[i], unwrapNull(vArr[i]))) {
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

    @Override // gnu.trove.THash
    public int setUp(int i) {
        int up = super.setUp(i);
        this._values = i == -1 ? (V[]) THash.EMPTY_OBJECT_ARRAY : (V[]) new Object[up];
        this._set = i == -1 ? null : new long[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TLongObjectProcedure<V>() { // from class: gnu.trove.TLongObjectHashMap.1
            @Override // gnu.trove.TLongObjectProcedure
            public boolean execute(long j, V v) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(j);
                sb.append('=');
                StringBuilder sb3 = sb;
                if (v == this) {
                    v = (V) "(this Map)";
                }
                sb3.append(v);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void transformValues(TObjectFunction<V, V> tObjectFunction) {
        Object[] objArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            if (isFull(objArr, i)) {
                objArr[i] = wrapNull(tObjectFunction.execute(unwrapNull(objArr[i])));
            }
            length = i;
        }
    }

    public TLongObjectHashMap(int i) {
        super(i);
        this._hashingStrategy = this;
    }

    public TLongObjectHashMap(int i, float f) {
        super(i, f);
        this._hashingStrategy = this;
    }

    public TLongObjectHashMap(TLongHashingStrategy tLongHashingStrategy) {
        this._hashingStrategy = tLongHashingStrategy;
    }

    public TLongObjectHashMap(int i, TLongHashingStrategy tLongHashingStrategy) {
        super(i);
        this._hashingStrategy = tLongHashingStrategy;
    }

    public TLongObjectHashMap(int i, float f, TLongHashingStrategy tLongHashingStrategy) {
        super(i, f);
        this._hashingStrategy = tLongHashingStrategy;
    }
}
