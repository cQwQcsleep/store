package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TObjectFloatHashMap<K> extends TObjectHash<K> {
    protected transient float[] _values;

    public static final class EqProcedure<K> implements TObjectFloatProcedure<K> {
        private final TObjectFloatHashMap<K> _otherMap;

        public EqProcedure(TObjectFloatHashMap<K> tObjectFloatHashMap) {
            this._otherMap = tObjectFloatHashMap;
        }

        private static boolean eq(float f, float f2) {
            return f == f2;
        }

        @Override // gnu.trove.TObjectFloatProcedure
        public final boolean execute(K k, float f) {
            return this._otherMap.index(k) >= 0 && eq(f, this._otherMap.get(k));
        }
    }

    public final class HashProcedure implements TObjectFloatProcedure<K> {
        private int h;

        public HashProcedure() {
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // gnu.trove.TObjectFloatProcedure
        public boolean execute(K k, float f) {
            this.h += TObjectFloatHashMap.this._hashingStrategy.computeHashCode((T) k) ^ HashFunctions.hash(f);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TObjectFloatHashMap() {
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
            put(objectInputStream.readObject(), objectInputStream.readFloat());
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

    public boolean adjustValue(K k, float f) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return false;
        }
        float[] fArr = this._values;
        fArr[iIndex] = fArr[iIndex] + f;
        return true;
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        Object[] objArr = this._set;
        float[] fArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            objArr[i] = null;
            fArr[i] = 0.0f;
            length = i;
        }
    }

    public boolean containsKey(K k) {
        return contains(k);
    }

    public boolean containsValue(float f) {
        Object[] objArr = this._set;
        float[] fArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return false;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && f == fArr[i]) {
                return true;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TObjectFloatHashMap)) {
            return false;
        }
        TObjectFloatHashMap tObjectFloatHashMap = (TObjectFloatHashMap) obj;
        if (tObjectFloatHashMap.size() != size()) {
            return false;
        }
        return forEachEntry(new EqProcedure(tObjectFloatHashMap));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachEntry(TObjectFloatProcedure<K> tObjectFloatProcedure) {
        Object[] objArr = this._set;
        float[] fArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && !tObjectFloatProcedure.execute(obj, fArr[i])) {
                return false;
            }
            length = i;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean forEachKey(TObjectProcedure<K> tObjectProcedure) {
        return forEach(tObjectProcedure);
    }

    public boolean forEachValue(TFloatProcedure tFloatProcedure) {
        Object[] objArr = this._set;
        float[] fArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED && !tFloatProcedure.execute(fArr[i])) {
                return false;
            }
            length = i;
        }
    }

    public float get(K k) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return 0.0f;
        }
        return this._values[iIndex];
    }

    public float[] getValues() {
        float[] fArr = new float[size()];
        float[] fArr2 = this._values;
        Object[] objArr = this._set;
        int length = objArr.length;
        int i = 0;
        while (true) {
            int i2 = length - 1;
            if (length <= 0) {
                return fArr;
            }
            Object obj = objArr[i2];
            if (obj != null && obj != TObjectHash.REMOVED) {
                fArr[i] = fArr2[i2];
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
        return adjustValue(k, 1.0f);
    }

    public TObjectFloatIterator<K> iterator() {
        return new TObjectFloatIterator<>(this);
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

    public float put(K k, float f) {
        float f2;
        boolean z;
        int iInsertionIndex = insertionIndex(k);
        if (iInsertionIndex < 0) {
            iInsertionIndex = (-iInsertionIndex) - 1;
            f2 = this._values[iInsertionIndex];
            z = false;
        } else {
            f2 = 0.0f;
            z = true;
        }
        Object[] objArr = this._set;
        Object obj = objArr[iInsertionIndex];
        objArr[iInsertionIndex] = k;
        this._values[iInsertionIndex] = f;
        if (z) {
            postInsertHook(obj == null);
        }
        return f2;
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        Object[] objArr = this._set;
        float[] fArr = this._values;
        this._set = new Object[i];
        this._values = new float[i];
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
                this._values[iInsertionIndex] = fArr[i2];
            }
            iCapacity = i2;
        }
    }

    public float remove(K k) {
        int iIndex = index(k);
        if (iIndex < 0) {
            return 0.0f;
        }
        float f = this._values[iIndex];
        removeAt(iIndex);
        return f;
    }

    @Override // gnu.trove.TObjectHash, gnu.trove.THash
    public void removeAt(int i) {
        this._values[i] = 0.0f;
        super.removeAt(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean retainEntries(TObjectFloatProcedure<K> tObjectFloatProcedure) {
        Object[] objArr = this._set;
        float[] fArr = this._values;
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
                if (obj != null && obj != TObjectHash.REMOVED && !tObjectFloatProcedure.execute(obj, fArr[i])) {
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
        this._values = i == -1 ? null : new float[up];
        return up;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEachEntry(new TObjectFloatProcedure<K>() { // from class: gnu.trove.TObjectFloatHashMap.1
            @Override // gnu.trove.TObjectFloatProcedure
            public boolean execute(K k, float f) {
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
                sb.append(f);
                return true;
            }
        });
        sb.append('}');
        sb.insert(0, '{');
        return sb.toString();
    }

    public void transformValues(TFloatFunction tFloatFunction) {
        Object[] objArr = this._set;
        float[] fArr = this._values;
        int length = objArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            Object obj = objArr[i];
            if (obj != null && obj != TObjectHash.REMOVED) {
                fArr[i] = tFloatFunction.execute(fArr[i]);
            }
            length = i;
        }
    }

    public TObjectFloatHashMap(int i) {
        super(i);
    }

    public TObjectFloatHashMap(int i, float f) {
        super(i, f);
    }

    public TObjectFloatHashMap(TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(tObjectHashingStrategy);
    }

    public TObjectFloatHashMap(int i, TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(i, tObjectHashingStrategy);
    }

    public TObjectFloatHashMap(int i, float f, TObjectHashingStrategy<K> tObjectHashingStrategy) {
        super(i, f, tObjectHashingStrategy);
    }
}
