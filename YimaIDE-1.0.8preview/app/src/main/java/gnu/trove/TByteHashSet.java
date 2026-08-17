package gnu.trove;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TByteHashSet extends TByteHash {

    public final class HashProcedure implements TByteProcedure {
        private int h;

        public HashProcedure() {
        }

        @Override // gnu.trove.TByteProcedure
        public final boolean execute(byte b) {
            this.h += TByteHashSet.this._hashingStrategy.computeHashCode(b);
            return true;
        }

        public int getHashCode() {
            return this.h;
        }
    }

    public TByteHashSet(byte[] bArr) {
        this(bArr.length);
        addAll(bArr);
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
            add(objectInputStream.readByte());
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

    public boolean add(byte b) {
        int iInsertionIndex = insertionIndex(b);
        if (iInsertionIndex < 0) {
            return false;
        }
        byte[] bArr = this._states;
        byte b2 = bArr[iInsertionIndex];
        this._set[iInsertionIndex] = b;
        bArr[iInsertionIndex] = 1;
        postInsertHook(b2 == 0);
        return true;
    }

    public boolean addAll(byte[] bArr) {
        int length = bArr.length;
        boolean z = false;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return z;
            }
            if (add(bArr[i])) {
                z = true;
            }
            length = i;
        }
    }

    @Override // gnu.trove.THash
    public void clear() {
        super.clear();
        byte[] bArr = this._set;
        byte[] bArr2 = this._states;
        if (bArr2 == null) {
            return;
        }
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return;
            }
            bArr[i] = 0;
            bArr2[i] = 0;
            length = i;
        }
    }

    public boolean containsAll(byte[] bArr) {
        int length = bArr.length;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return true;
            }
            if (!contains(bArr[i])) {
                return false;
            }
            length = i;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TByteHashSet)) {
            return false;
        }
        final TByteHashSet tByteHashSet = (TByteHashSet) obj;
        if (tByteHashSet.size() != size()) {
            return false;
        }
        return forEach(new TByteProcedure() { // from class: gnu.trove.TByteHashSet.1
            @Override // gnu.trove.TByteProcedure
            public final boolean execute(byte b) {
                return tByteHashSet.contains(b);
            }
        });
    }

    public int hashCode() {
        HashProcedure hashProcedure = new HashProcedure();
        forEach(hashProcedure);
        return hashProcedure.getHashCode();
    }

    public TByteIterator iterator() {
        return new TByteIterator(this);
    }

    @Override // gnu.trove.THash
    public void rehash(int i) {
        int iCapacity = capacity();
        byte[] bArr = this._set;
        byte[] bArr2 = this._states;
        this._set = new byte[i];
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
                this._states[iInsertionIndex] = 1;
            }
            iCapacity = i2;
        }
    }

    public boolean remove(byte b) {
        int iIndex = index(b);
        if (iIndex < 0) {
            return false;
        }
        removeAt(iIndex);
        return true;
    }

    public boolean removeAll(byte[] bArr) {
        int length = bArr.length;
        boolean z = false;
        while (true) {
            int i = length - 1;
            if (length <= 0) {
                return z;
            }
            if (remove(bArr[i])) {
                z = true;
            }
            length = i;
        }
    }

    public boolean retainAll(byte[] bArr) {
        Arrays.sort(bArr);
        byte[] bArr2 = this._set;
        byte[] bArr3 = this._states;
        boolean z = false;
        if (bArr2 != null) {
            int length = bArr2.length;
            while (true) {
                length--;
                if (length <= 0) {
                    break;
                }
                if (bArr3[length] == 1 && Arrays.binarySearch(bArr, bArr2[length]) < 0) {
                    remove(bArr2[length]);
                    z = true;
                }
            }
        }
        return z;
    }

    public byte[] toArray() {
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

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEach(new TByteProcedure() { // from class: gnu.trove.TByteHashSet.2
            @Override // gnu.trove.TByteProcedure
            public boolean execute(byte b) {
                if (sb.length() != 0) {
                    StringBuilder sb2 = sb;
                    sb2.append(',');
                    sb2.append(' ');
                }
                sb.append((int) b);
                return true;
            }
        });
        sb.append(']');
        sb.insert(0, '[');
        return sb.toString();
    }

    public TByteHashSet(int i) {
        super(i);
    }

    public TByteHashSet(int i, float f) {
        super(i, f);
    }

    public TByteHashSet() {
    }

    public TByteHashSet(TByteHashingStrategy tByteHashingStrategy) {
        super(tByteHashingStrategy);
    }

    public TByteHashSet(int i, TByteHashingStrategy tByteHashingStrategy) {
        super(i, tByteHashingStrategy);
    }

    public TByteHashSet(int i, float f, TByteHashingStrategy tByteHashingStrategy) {
        super(i, f, tByteHashingStrategy);
    }

    public TByteHashSet(byte[] bArr, TByteHashingStrategy tByteHashingStrategy) {
        this(bArr.length, tByteHashingStrategy);
        addAll(bArr);
    }
}
