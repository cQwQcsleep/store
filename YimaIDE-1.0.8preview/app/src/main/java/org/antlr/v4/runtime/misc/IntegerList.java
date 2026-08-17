package org.antlr.v4.runtime.misc;

import defpackage.j2d;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class IntegerList {
    private static final int[] EMPTY_DATA = new int[0];
    private static final int INITIAL_SIZE = 4;
    private static final int MAX_ARRAY_SIZE = 2147483639;
    private int[] _data;
    private int _size;

    public IntegerList(Collection<Integer> collection) {
        this(collection.size());
        Iterator<Integer> it = collection.iterator();
        while (it.hasNext()) {
            add(it.next().intValue());
        }
    }

    private int charArraySize() {
        int iCharCount = 0;
        for (int i = 0; i < this._size; i++) {
            iCharCount += Character.charCount(this._data[i]);
        }
        return iCharCount;
    }

    private void ensureCapacity(int i) {
        if (i < 0 || i > MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError();
        }
        int[] iArr = this._data;
        int length = iArr.length == 0 ? 4 : iArr.length;
        while (length < i) {
            length *= 2;
            if (length < 0 || length > MAX_ARRAY_SIZE) {
                length = MAX_ARRAY_SIZE;
            }
        }
        this._data = Arrays.copyOf(this._data, length);
    }

    public final void add(int i) {
        int length = this._data.length;
        int i2 = this._size;
        if (length == i2) {
            ensureCapacity(i2 + 1);
        }
        int[] iArr = this._data;
        int i3 = this._size;
        iArr[i3] = i;
        this._size = i3 + 1;
    }

    public final void addAll(Collection<Integer> collection) {
        ensureCapacity(this._size + collection.size());
        Iterator<Integer> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            this._data[this._size + i] = it.next().intValue();
            i++;
        }
        this._size += collection.size();
    }

    public final int binarySearch(int i, int i2, int i3) {
        int i4;
        if (i < 0 || i2 < 0 || i > (i4 = this._size) || i2 > i4) {
            qc6.a();
            return 0;
        }
        if (i <= i2) {
            return Arrays.binarySearch(this._data, i, i2, i3);
        }
        j2d.a();
        return 0;
    }

    public final void clear() {
        Arrays.fill(this._data, 0, this._size, 0);
        this._size = 0;
    }

    public final boolean contains(int i) {
        for (int i2 = 0; i2 < this._size; i2++) {
            if (this._data[i2] == i) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntegerList)) {
            return false;
        }
        IntegerList integerList = (IntegerList) obj;
        if (this._size != integerList._size) {
            return false;
        }
        for (int i = 0; i < this._size; i++) {
            if (this._data[i] != integerList._data[i]) {
                return false;
            }
        }
        return true;
    }

    public final int get(int i) {
        if (i >= 0 && i < this._size) {
            return this._data[i];
        }
        qc6.a();
        return 0;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this._size; i2++) {
            i = (i * 31) + this._data[i2];
        }
        return i;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final int removeAt(int i) {
        int i2 = get(i);
        int[] iArr = this._data;
        System.arraycopy(iArr, i + 1, iArr, i, (this._size - i) - 1);
        int[] iArr2 = this._data;
        int i3 = this._size;
        iArr2[i3 - 1] = 0;
        this._size = i3 - 1;
        return i2;
    }

    public final void removeRange(int i, int i2) {
        int i3;
        if (i < 0 || i2 < 0 || i > (i3 = this._size) || i2 > i3) {
            qc6.a();
            return;
        }
        if (i > i2) {
            j2d.a();
            return;
        }
        int[] iArr = this._data;
        System.arraycopy(iArr, i2, iArr, i, i3 - i2);
        int[] iArr2 = this._data;
        int i4 = this._size;
        int i5 = i2 - i;
        Arrays.fill(iArr2, i4 - i5, i4, 0);
        this._size -= i5;
    }

    public final int set(int i, int i2) {
        if (i < 0 || i >= this._size) {
            qc6.a();
            return 0;
        }
        int[] iArr = this._data;
        int i3 = iArr[i];
        iArr[i] = i2;
        return i3;
    }

    public final int size() {
        return this._size;
    }

    public final void sort() {
        Arrays.sort(this._data, 0, this._size);
    }

    public final int[] toArray() {
        int i = this._size;
        return i == 0 ? EMPTY_DATA : Arrays.copyOf(this._data, i);
    }

    public final char[] toCharArray() {
        char[] cArrCopyOf = new char[this._size];
        boolean z = false;
        int chars = 0;
        for (int i = 0; i < this._size; i++) {
            int i2 = this._data[i];
            if (!z && Character.isSupplementaryCodePoint(i2)) {
                cArrCopyOf = Arrays.copyOf(cArrCopyOf, charArraySize());
                z = true;
            }
            chars += Character.toChars(i2, cArrCopyOf, chars);
        }
        return cArrCopyOf;
    }

    public String toString() {
        return Arrays.toString(toArray());
    }

    public final void trimToSize() {
        int[] iArr = this._data;
        int length = iArr.length;
        int i = this._size;
        if (length == i) {
            return;
        }
        this._data = Arrays.copyOf(iArr, i);
    }

    public final int binarySearch(int i) {
        return Arrays.binarySearch(this._data, 0, this._size, i);
    }

    public IntegerList(int i) {
        if (i < 0) {
            j2d.a();
            throw null;
        }
        if (i == 0) {
            this._data = EMPTY_DATA;
        } else {
            this._data = new int[i];
        }
    }

    public IntegerList(IntegerList integerList) {
        this._data = (int[]) integerList._data.clone();
        this._size = integerList._size;
    }

    public IntegerList() {
        this._data = EMPTY_DATA;
    }

    public final void addAll(IntegerList integerList) {
        ensureCapacity(this._size + integerList._size);
        System.arraycopy(integerList._data, 0, this._data, this._size, integerList._size);
        this._size += integerList._size;
    }

    public final void addAll(int[] iArr) {
        ensureCapacity(this._size + iArr.length);
        System.arraycopy(iArr, 0, this._data, this._size, iArr.length);
        this._size += iArr.length;
    }
}
