package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TIntObjectHashMap<V> extends THash implements TIntHashingStrategy {
    protected final TIntHashingStrategy _hashingStrategy;
    protected transient int[] _set;
    protected transient V[] _values;

    public static final class EqProcedure<V> implements TIntObjectProcedure<V> {
        private final TIntObjectHashMap<V> _otherMap;

        public EqProcedure(TIntObjectHashMap<V> tIntObjectHashMap) {
            this._otherMap = tIntObjectHashMap;
        }

        private static boolean eq(Object obj, Object obj2) {
            if (obj != obj2) {
                return obj != null && obj.equals(obj2);
            }
            return true;
        }

        @Override // gnu.trove.TIntObjectProcedure
        public final boolean execute(int i, V v) {
            return this._otherMap.index(i) >= 0 && eq(v, this._otherMap.get(i));
        }
    }

    public final class HashProcedure implements TIntObjectProcedure<V> {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TIntObjectProcedure
        public final boolean execute(int i, V v) {
            this.h += TIntObjectHashMap.this._hashingStrategy.computeHashCode(i) ^ HashFunctions.hash(v);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TIntObjectHashMap() {
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
            put(objectInputStream.readInt(), objectInputStream.readObject());
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
        int[] iArr = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            iArr[i] = 0;
            vArr[i] = null;
            length = i;
        }
    }

    @Override // gnu.trove.THash
    public TIntObjectHashMap<V> clone() {
        TIntObjectHashMap<V> tIntObjectHashMap = (TIntObjectHashMap) super.clone();
        V[] vArr = this._values;
        Object[] objArr = THash.EMPTY_OBJECT_ARRAY;
        tIntObjectHashMap._values = vArr == objArr ? (V[]) objArr : (V[]) ((Object[]) vArr.clone());
        tIntObjectHashMap._set = this._values == objArr ? null : (int[]) this._set.clone();
        return tIntObjectHashMap;
    }

    @Override // gnu.trove.TIntHashingStrategy
    public final int computeHashCode(int i) {
        return HashFunctions.hash(i);
    }

    public boolean contains(int i) {
        return index(i) >= 0;
    }

    public boolean containsKey(int i) {
        return contains(i);
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
        if (!(obj instanceof TIntObjectHashMap)) {
            return false;
        }
        TIntObjectHashMap tIntObjectHashMap = (TIntObjectHashMap) obj;
        if (tIntObjectHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tIntObjectHashMap));
    }

    public boolean forEach(TIntProcedure tIntProcedure) {
        int[] iArr = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (isFull(vArr, i) && !tIntProcedure.execute(iArr[i])) {
                return false;
            }
            length = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachEntry(TIntObjectProcedure<V> tIntObjectProcedure) {
        int[] iArr = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (isFull(vArr, i) && !tIntObjectProcedure.execute(iArr[i], unwrapNull(vArr[i]))) {
                return false;
            }
            length = i;
        }
    }

    public boolean forEachKey(TIntProcedure tIntProcedure) {
        return forEach(tIntProcedure);
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

    public V get(int i) {
        int iIndex = index(i);
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

    public int index(int i) {
        int[] iArr = this._set;
        V[] vArr = this._values;
        if (vArr == THash.EMPTY_OBJECT_ARRAY) {
            return -1;
        }
        int length = iArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(i) & Integer.MAX_VALUE;
        int i2 = iComputeHashCode % length;
        if (!isFree(vArr, i2) && (isRemoved(vArr, i2) || iArr[i2] != i)) {
            int i3 = (iComputeHashCode % (length - 2)) + 1;
            while (true) {
                i2 -= i3;
                if (i2 < 0) {
                    i2 += length;
                }
                if (isFree(vArr, i2) || (!isRemoved(vArr, i2) && iArr[i2] == i)) {
                    break;
                }
            }
        }
        if (isFree(vArr, i2)) {
            return -1;
        }
        return i2;
    }

    public int insertionIndex(int i) {
        if (this._values == THash.EMPTY_OBJECT_ARRAY) {
            setUp(6);
        }
        V[] vArr = this._values;
        int[] iArr = this._set;
        int length = iArr.length;
        int iComputeHashCode = this._hashingStrategy.computeHashCode(i) & Integer.MAX_VALUE;
        int i2 = iComputeHashCode % length;
        if (isFree(vArr, i2)) {
            return i2;
        }
        if (!isFull(vArr, i2) || iArr[i2] != i) {
            int i3 = (iComputeHashCode % (length - 2)) + 1;
            int i4 = isRemoved(vArr, i2) ? i2 : -1;
            do {
                i2 -= i3;
                if (i2 < 0) {
                    i2 += length;
                }
                if (i4 == -1 && isRemoved(vArr, i2)) {
                    i4 = i2;
                }
                if (!isFull(vArr, i2)) {
                    break;
                }
            } while (iArr[i2] != i);
            if (isRemoved(vArr, i2)) {
                while (!isFree(vArr, i2) && (isRemoved(vArr, i2) || iArr[i2] != i)) {
                    i2 -= i3;
                    if (i2 < 0) {
                        i2 += length;
                    }
                }
            }
            if (!isFull(vArr, i2)) {
                return i4 == -1 ? i2 : i4;
            }
        }
        return (-i2) - 1;
    }

    public TIntObjectIterator<V> iterator() {
        return new TIntObjectIterator<>(this);
    }

    public int[] keys() {
        int[] iArr = new int[size()];
        int[] iArr2 = this._set;
        V[] vArr = this._values;
        int length = vArr.length;
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return iArr;
            }
            if (isFull(vArr, i2)) {
                iArr[i] = iArr2[i2];
                i++;
            }
            length = i2;
        }
    }

    public V put(int i, V v) {
        boolean zIsFree;
        V v2;
        int iInsertionIndex = insertionIndex(i);
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
        this._set[iInsertionIndex] = i;
        ((V[]) this._values)[iInsertionIndex] = wrapNull(v);
        if (z) {
            postInsertHook(zIsFree);
        }
        return v2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        int[] iArr = this._set;
        V[] vArr = this._values;
        this._set = new int[i];
        this._values = (V[]) new Object[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (isFull(vArr, i2)) {
                int i3 = iArr[i2];
                int iInsertionIndex = insertionIndex(i3);
                this._set[iInsertionIndex] = i3;
                this._values[iInsertionIndex] = vArr[i2];
            }
            iCapacity = i2;
        }
    }

    public V remove(int i) {
        int iIndex = index(i);
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
    public boolean retainEntries(TIntObjectProcedure<V> tIntObjectProcedure) {
        int[] iArr = this._set;
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
                if (isFull(vArr, i) && !tIntObjectProcedure.execute(iArr[i], unwrapNull(vArr[i]))) {
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
        this._set = i == -1 ? null : new int[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TIntObjectProcedure<V>() { // from class: gnu.trove.TIntObjectHashMap.1
            @Override // gnu.trove.TIntObjectProcedure
            public boolean execute(int i, V v) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(i);
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

    public TIntObjectHashMap(int i) {
        super(i);
        this._hashingStrategy = this;
    }

    public TIntObjectHashMap(int i, float f) {
        super(i, f);
        this._hashingStrategy = this;
    }

    public TIntObjectHashMap(TIntHashingStrategy tIntHashingStrategy) {
        this._hashingStrategy = tIntHashingStrategy;
    }

    public TIntObjectHashMap(int i, TIntHashingStrategy tIntHashingStrategy) {
        super(i);
        this._hashingStrategy = tIntHashingStrategy;
    }

    public TIntObjectHashMap(int i, float f, TIntHashingStrategy tIntHashingStrategy) {
        super(i, f);
        this._hashingStrategy = tIntHashingStrategy;
    }
}
