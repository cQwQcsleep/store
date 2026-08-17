package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TObjectDoubleHashMap<K> extends TObjectHash<K> {
    protected transient double[] _values;

    public static final class EqProcedure<K> implements TObjectDoubleProcedure<K> {
        private final TObjectDoubleHashMap<K> _otherMap;

        public EqProcedure(TObjectDoubleHashMap<K> tObjectDoubleHashMap) {
            this._otherMap = tObjectDoubleHashMap;
        }

        private static boolean eq(double d, double d2) {
            return d == d2;
        }

        @Override // gnu.trove.TObjectDoubleProcedure
        public final boolean execute(K k, double d) {
            return this._otherMap.index(k) >= 0 && eq(d, this._otherMap.get(k));
        }
    }

    public final class HashProcedure implements TObjectDoubleProcedure<K> {
        private int h;

        public HashProcedure() {
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // gnu.trove.TObjectDoubleProcedure
        public boolean execute(K k, double d) {
            this.h += TObjectDoubleHashMap.this._hashingStrategy.computeHashCode((T) k) ^ HashFunctions.hash(d);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TObjectDoubleHashMap() {
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
            put(objectInputStream.readObject(), objectInputStream.readDouble());
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

    public boolean adjustValue(K k, double d) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return false;
        }
        double[] dArr = this._values;
        dArr[iIndex] = dArr[iIndex] + d;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        Object[] objArr = this._set;
        double[] dArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            objArr[i] = null;
            dArr[i] = 0.0d;
            length = i;
        }
    }

    public boolean containsKey(K k) {
        return contains(k);
    }

    public boolean containsValue(double d) {
        Object[] objArr = this._set;
        double[] dArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && d == dArr[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TObjectDoubleHashMap)) {
            return false;
        }
        TObjectDoubleHashMap tObjectDoubleHashMap = (TObjectDoubleHashMap) obj;
        if (tObjectDoubleHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tObjectDoubleHashMap));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachEntry(TObjectDoubleProcedure<K> tObjectDoubleProcedure) {
        Object[] objArr = this._set;
        double[] dArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && !tObjectDoubleProcedure.execute(obj, dArr[i])) {
                return false;
            }
            length = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachKey(TObjectProcedure<K> tObjectProcedure) {
        return forEach(tObjectProcedure);
    }

    public boolean forEachValue(TDoubleProcedure tDoubleProcedure) {
        Object[] objArr = this._set;
        double[] dArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && !tDoubleProcedure.execute(dArr[i])) {
                return false;
            }
            length = i;
        }
    }

    public double get(K k) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return 0.0d;
        }
        return this._values[iIndex];
    }

    public double[] getValues() {
        double[] dArr = new double[size()];
        double[] dArr2 = this._values;
        Object[] objArr = this._set;
        int length = objArr.length;
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return dArr;
            }
            Object obj = objArr[i2];
            if (obj != null && obj != TObjectHash.REMOVED) {
                dArr[i] = dArr2[i2];
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
        return adjustValue(k, 1.0d);
    }

    public TObjectDoubleIterator<K> iterator() {
        return new TObjectDoubleIterator<>(this);
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

    public double put(K k, double d) {
        double d2;
        boolean z;
        int iInsertionIndex = insertionIndex(k);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            d2 = this._values[iInsertionIndex];
            z = false;
        } else {
            d2 = 0.0d;
            z = true;
        }
        Object[] objArr = this._set;
        Object obj = objArr[iInsertionIndex];
        objArr[iInsertionIndex] = k;
        this._values[iInsertionIndex] = d;
        if (z) {
            postInsertHook(obj == null);
        }
        return d2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        Object[] objArr = this._set;
        double[] dArr = this._values;
        this._set = new Object[i];
        this._values = new double[i];
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
                this._values[iInsertionIndex] = dArr[i2];
            }
            iCapacity = i2;
        }
    }

    public double remove(K k) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return 0.0d;
        }
        double d = this._values[iIndex];
        removeAt(iIndex);
        return d;
    }

    @Override // gnu.trove.TObjectHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0d;
        super.removeAt(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean retainEntries(TObjectDoubleProcedure<K> tObjectDoubleProcedure) {
        Object[] objArr = this._set;
        double[] dArr = this._values;
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
                if (obj != null && obj != TObjectHash.REMOVED && !tObjectDoubleProcedure.execute(obj, dArr[i])) {
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
        this._values = i == -1 ? null : new double[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TObjectDoubleProcedure<K>() { // from class: gnu.trove.TObjectDoubleHashMap.1
            @Override // gnu.trove.TObjectDoubleProcedure
            public boolean execute(K k, double d) {
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
                sb.append(d);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TDoubleFunction tDoubleFunction) {
        Object[] objArr = this._set;
        double[] dArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED) {
                dArr[i] = tDoubleFunction.execute(dArr[i]);
            }
            length = i;
        }
    }

    public TObjectDoubleHashMap(int i) {
        super(i);
    }

    public TObjectDoubleHashMap(int i, float f) {
        super(i, f);
    }

    public TObjectDoubleHashMap(TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(tObjectHashingStrategy);
    }

    public TObjectDoubleHashMap(int i, TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(i, tObjectHashingStrategy);
    }

    public TObjectDoubleHashMap(int i, float f, TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(i, f, tObjectHashingStrategy);
    }
}
