package gnu.trove;

import defpackage.y0e;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TIntArrayList implements Serializable, Cloneable {
    protected static final int DEFAULT_CAPACITY = 4;
    protected transient int[] _data;
    protected transient int _pos;

    public TIntArrayList(int[] iArr) {
        this(Math.max(iArr.length, 4));
        add(iArr);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int i = objectInputStream.readInt();
        this._data = new int[i];
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            add(objectInputStream.readInt());
            i = i2;
        }
    }

    private void swap(int i, int i2) {
        int[] iArr = this._data;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        SerializationProcedure serializationProcedure = new SerializationProcedure(objectOutputStream);
        if (!forEach(serializationProcedure)) {
            throw serializationProcedure.exception;
        }
    }

    public void add(int[] iArr, int i, int i2) {
        ensureCapacity(this._pos + i2);
        System.arraycopy(iArr, i, this._data, this._pos, i2);
        this._pos += i2;
    }

    public int binarySearch(int i, int i2, int i3) {
        if (i2 < 0) {
            y0e.a(i2);
            return 0;
        }
        if (i3 > this._pos) {
            y0e.a(i3);
            return 0;
        }
        int i4 = i3 - 1;
        while (i2 <= i4) {
            int i5 = (i2 + i4) >> 1;
            int i6 = this._data[i5];
            if (i6 < i) {
                i2 = i5 + 1;
            } else {
                if (i6 <= i) {
                    return i5;
                }
                i4 = i5 - 1;
            }
        }
        return -(i2 + 1);
    }

    public void clear(int i) {
        this._data = new int[i];
        this._pos = 0;
    }

    public Object clone() {
        int[] iArr = null;
        try {
            TIntArrayList tIntArrayList = (TIntArrayList) super.clone();
            try {
                int[] iArr2 = this._data;
                if (iArr2 != null) {
                    iArr = (int[]) iArr2.clone();
                }
                tIntArrayList._data = iArr;
                return tIntArrayList;
            } catch (CloneNotSupportedException unused) {
                return tIntArrayList;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public boolean contains(int i) {
        return lastIndexOf(i) >= 0;
    }

    public void ensureCapacity(int i) {
        if (this._data == null) {
            this._data = new int[Math.max(4, i)];
        }
        int[] iArr = this._data;
        if (i > iArr.length) {
            int[] iArr2 = new int[Math.max(iArr.length << 1, i)];
            int[] iArr3 = this._data;
            System.arraycopy(iArr3, 0, iArr2, 0, iArr3.length);
            this._data = iArr2;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TIntArrayList)) {
            return false;
        }
        TIntArrayList tIntArrayList = (TIntArrayList) obj;
        if (tIntArrayList.size() != size()) {
            return false;
        }
        int i = this._pos;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return true;
            }
            if (this._data[i2] != tIntArrayList._data[i2]) {
                return false;
            }
            i = i2;
        }
    }

    public void fill(int i, int i2, int i3) {
        if (i2 > this._pos) {
            ensureCapacity(i2);
            this._pos = i2;
        }
        if (isEmpty()) {
            return;
        }
        Arrays.fill(this._data, i, i2, i3);
    }

    public boolean forEach(TIntProcedure tIntProcedure) {
        for (int i = 0; i < this._pos; i++) {
            if (!tIntProcedure.execute(this._data[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean forEachDescending(TIntProcedure tIntProcedure) {
        int i = this._pos;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return true;
            }
            if (!tIntProcedure.execute(this._data[i2])) {
                return false;
            }
            i = i2;
        }
    }

    public int get(int i) {
        if (i < this._pos) {
            return this._data[i];
        }
        y0e.a(i);
        return 0;
    }

    public int getQuick(int i) {
        return this._data[i];
    }

    public int getSet(int i, int i2) {
        if (i < 0 || i >= this._pos) {
            y0e.a(i);
            return 0;
        }
        int[] iArr = this._data;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    public TIntArrayList grep(TIntProcedure tIntProcedure) {
        TIntArrayList tIntArrayList = new TIntArrayList();
        for (int i = 0; i < this._pos; i++) {
            if (tIntProcedure.execute(this._data[i])) {
                tIntArrayList.add(this._data[i]);
            }
        }
        return tIntArrayList;
    }

    public int hashCode() {
        int i = this._pos;
        int iHash = 0;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return iHash;
            }
            iHash += HashFunctions.hash(this._data[i2]);
            i = i2;
        }
    }

    public int indexOf(int i, int i2) {
        while (i < this._pos) {
            if (this._data[i] == i2) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public void insert(int i, int i2) {
        int i3 = this._pos;
        if (i == i3) {
            add(i2);
            return;
        }
        ensureCapacity(i3 + 1);
        int[] iArr = this._data;
        System.arraycopy(iArr, i, iArr, i + 1, this._pos - i);
        this._data[i] = i2;
        this._pos++;
    }

    public TIntArrayList inverseGrep(TIntProcedure tIntProcedure) {
        TIntArrayList tIntArrayList = new TIntArrayList();
        for (int i = 0; i < this._pos; i++) {
            if (!tIntProcedure.execute(this._data[i])) {
                tIntArrayList.add(this._data[i]);
            }
        }
        return tIntArrayList;
    }

    public boolean isEmpty() {
        return this._pos == 0;
    }

    public int lastIndexOf(int i, int i2) {
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return -1;
            }
            if (this._data[i3] == i2) {
                return i3;
            }
            i = i3;
        }
    }

    public int max() {
        if (size() == 0) {
            k2d.a("cannot find maximum of an empty list");
            return 0;
        }
        int[] iArr = this._data;
        int i = this._pos;
        int iMax = iArr[i - 1];
        int i2 = i - 1;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return iMax;
            }
            iMax = Math.max(iMax, this._data[this._pos]);
            i2 = i3;
        }
    }

    public int min() {
        if (size() == 0) {
            k2d.a("cannot find minimum of an empty list");
            return 0;
        }
        int[] iArr = this._data;
        int i = this._pos;
        int iMin = iArr[i - 1];
        int i2 = i - 1;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return iMin;
            }
            iMin = Math.min(iMin, this._data[this._pos]);
            i2 = i3;
        }
    }

    public void remove(int i, int i2) {
        int i3;
        if (i < 0 || i >= (i3 = this._pos)) {
            y0e.a(i);
            return;
        }
        if (i == 0) {
            int[] iArr = this._data;
            System.arraycopy(iArr, i2, iArr, 0, i3 - i2);
        } else if (i3 - i2 != i) {
            int[] iArr2 = this._data;
            int i4 = i + i2;
            System.arraycopy(iArr2, i4, iArr2, i, i3 - i4);
        }
        this._pos -= i2;
    }

    public void reset() {
        fill(0);
        this._pos = 0;
    }

    public void resetQuick() {
        this._pos = 0;
    }

    public void reverse(int i, int i2) {
        if (i == i2) {
            return;
        }
        if (i > i2) {
            w01.a("from cannot be greater than to");
            return;
        }
        for (int i3 = i2 - 1; i < i3; i3--) {
            swap(i, i3);
            i++;
        }
    }

    public void set(int i, int[] iArr, int i2, int i3) {
        if (i < 0 || i + i3 > this._pos) {
            y0e.a(i);
        } else {
            System.arraycopy(this._data, i, iArr, i2, i3);
        }
    }

    public void setQuick(int i, int i2) {
        this._data[i] = i2;
    }

    public void shuffle(Random random) {
        int i = this._pos;
        while (true) {
            int i2 = i - 1;
            if (i <= 1) {
                return;
            }
            swap(i2, random.nextInt(i2));
            i = i2;
        }
    }

    public int size() {
        return this._pos;
    }

    public void sort() {
        if (isEmpty()) {
            return;
        }
        Arrays.sort(this._data, 0, this._pos);
    }

    public void toNativeArray(int[] iArr, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        if (i < 0 || i >= this._pos) {
            y0e.a(i);
        } else {
            System.arraycopy(this._data, i, iArr, 0, i2);
        }
    }

    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer("{");
        forEach(new TIntProcedure() { // from class: gnu.trove.TIntArrayList.1
            @Override // gnu.trove.TIntProcedure
            public boolean execute(int i) {
                stringBuffer.append(i);
                stringBuffer.append(", ");
                return true;
            }
        });
        stringBuffer.append("}");
        return stringBuffer.toString();
    }

    public void transformValues(TIntFunction tIntFunction) {
        int i = this._pos;
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            int[] iArr = this._data;
            iArr[i2] = tIntFunction.execute(iArr[i2]);
            i = i2;
        }
    }

    public void trimToSize() {
        int[] iArr = this._data;
        if (iArr == null || iArr.length <= size()) {
            return;
        }
        int size = size();
        int[] iArr2 = new int[size];
        toNativeArray(iArr2, 0, size);
        this._data = iArr2;
    }

    public void clear() {
        this._data = null;
        this._pos = 0;
    }

    public TIntArrayList(int i) {
        this._data = new int[i];
        this._pos = 0;
    }

    public int lastIndexOf(int i) {
        return lastIndexOf(this._pos, i);
    }

    public void sort(int i, int i2) {
        if (isEmpty()) {
            return;
        }
        Arrays.sort(this._data, i, i2);
    }

    public TIntArrayList() {
    }

    public int indexOf(int i) {
        return indexOf(0, i);
    }

    public void set(int i, int[] iArr) {
        set(i, iArr, 0, iArr.length);
    }

    public void add(int[] iArr) {
        add(iArr, 0, iArr.length);
    }

    public void set(int i, int i2) {
        if (i >= 0 && i < this._pos) {
            this._data[i] = i2;
        } else {
            y0e.a(i);
        }
    }

    public void add(int i) {
        ensureCapacity(this._pos + 1);
        int[] iArr = this._data;
        int i2 = this._pos;
        this._pos = i2 + 1;
        iArr[i2] = i;
    }

    public int[] toNativeArray(int i, int i2) {
        int[] iArr = new int[i2];
        toNativeArray(iArr, i, i2);
        return iArr;
    }

    public void fill(int i) {
        if (isEmpty()) {
            return;
        }
        Arrays.fill(this._data, 0, this._pos, i);
    }

    public int[] toNativeArray() {
        return toNativeArray(0, this._pos);
    }

    public void reverse() {
        reverse(0, this._pos);
    }

    public void insert(int i, int[] iArr) {
        insert(i, iArr, 0, iArr.length);
    }

    public void insert(int i, int[] iArr, int i2, int i3) {
        int i4 = this._pos;
        if (i == i4) {
            add(iArr, i2, i3);
            return;
        }
        ensureCapacity(i4 + i3);
        int[] iArr2 = this._data;
        System.arraycopy(iArr2, i, iArr2, i + i3, this._pos - i);
        System.arraycopy(iArr, i2, this._data, i, i3);
        this._pos += i3;
    }

    public int remove(int i) {
        int i2 = get(i);
        remove(i, 1);
        return i2;
    }

    public int binarySearch(int i) {
        return binarySearch(i, 0, this._pos);
    }
}
