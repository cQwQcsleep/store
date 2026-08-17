package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TDoubleHashSet extends TDoubleHash {

    public final class HashProcedure implements TDoubleProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TDoubleProcedure
        public final boolean execute(double d) {
            this.h += TDoubleHashSet.this._hashingStrategy.computeHashCode(d);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TDoubleHashSet(double[] dArr) {
        this(dArr.length);
        addAll(dArr);
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
            add(objectInputStream.readDouble());
            i = i2;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(this._size);
        SerializationProcedure serializationProcedure = new SerializationProcedure(objectOutputStream);
        if (!forEach(serializationProcedure)) {
            throw serializationProcedure.exception;
        }
    }

    public boolean add(double d) {
        int iInsertionIndex = insertionIndex(d);
        if (iInsertionIndex < 0) {
            return false;
        }
        byte[] bArr = this._states;
        byte b = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = d;
        bArr[iInsertionIndex] = 1;
        postInsertHook(b == 0);
        return true;
    }

    public boolean addAll(double[] dArr) {
        int length = dArr.length;
        boolean z = false;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return z;
            }
            if (add(dArr[i])) {
                z = true;
            }
            length = i;
        }
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        double[] dArr = this._set;
        byte[] bArr = this._states;
        if (bArr == null) {
            return;
        }
        int length = dArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            dArr[i] = 0.0d;
            bArr[i] = 0;
            length = i;
        }
    }

    public boolean containsAll(double[] dArr) {
        int length = dArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (!contains(dArr[i])) {
                return false;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TDoubleHashSet)) {
            return false;
        }
        final TDoubleHashSet tDoubleHashSet = (TDoubleHashSet) obj;
        if (tDoubleHashSet.size() != size()) {
            return false;
        }
        return forEach(new TDoubleProcedure() { // from class: gnu.trove.TDoubleHashSet.1
            @Override // gnu.trove.TDoubleProcedure
            public final boolean execute(double d) {
                return tDoubleHashSet.contains(d);
            }
        });
    }

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEach(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public TDoubleIterator iterator() {
        return new TDoubleIterator(this);
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        double[] dArr = this._set;
        byte[] bArr = this._states;
        this._set = new double[i];
        this._states = new byte[i];
        while (true) {
            int i2 = iCapacity - 1;
            if (iCapacity <= 0) {
                return;
            }
            if (bArr[i2] == 1) {
                double d = dArr[i2];
                int iInsertionIndex = insertionIndex(d);
                this._set[iInsertionIndex] = d;
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public boolean remove(double d) {
        int iIndex = index(d);
        if (iIndex < 0) {
            return false;
        }
        removeAt(iIndex);
        return true;
    }

    public boolean removeAll(double[] dArr) {
        int length = dArr.length;
        boolean z = false;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return z;
            }
            if (remove(dArr[i])) {
                z = true;
            }
            length = i;
        }
    }

    public boolean retainAll(double[] dArr) {
        Arrays.sort(dArr);
        double[] dArr2 = this._set;
        byte[] bArr = this._states;
        boolean z = false;
        if (dArr2 != null) {
            int length = dArr2.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr[length] == 1 && Arrays.binarySearch(dArr, dArr2[length]) < 0) {
                    remove(dArr2[length]);
                    z = true;
                }
            }
        }
        return z;
    }

    public double[] toArray() {
        double[] dArr = new double[size()];
        double[] dArr2 = this._set;
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
                    dArr[i] = dArr2[i2];
                    i++;
                }
                length = i2;
            }
        }
        return dArr;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEach(new TDoubleProcedure() { // from class: gnu.trove.TDoubleHashSet.2
            @Override // gnu.trove.TDoubleProcedure
            public boolean execute(double d) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append(d);
                return true;
            }
        });
        sb.append(']');
        sb.insert(0, '[');
        return sb.toString();
    }

    public TDoubleHashSet(int i) {
        super(i);
    }

    public TDoubleHashSet(int i, float f) {
        super(i, f);
    }

    public TDoubleHashSet() {
    }

    public TDoubleHashSet(TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(tDoubleHashingStrategy);
    }

    public TDoubleHashSet(int i, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, tDoubleHashingStrategy);
    }

    public TDoubleHashSet(int i, float f, TDoubleHashingStrategy tDoubleHashingStrategy) {
        super(i, f, tDoubleHashingStrategy);
    }

    public TDoubleHashSet(double[] dArr, TDoubleHashingStrategy tDoubleHashingStrategy) {
        this(dArr.length, tDoubleHashingStrategy);
        addAll(dArr);
    }
}
