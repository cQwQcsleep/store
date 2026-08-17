package com.reandroid.arsc.item;

import com.reandroid.arsc.base.Block;
import com.reandroid.common.IntegerArray;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ShortArrayBlock extends BlockItem implements IntegerArray {
    public ShortArrayBlock() {
        super(0);
    }

    public void add(IntegerArray integerArray) {
        if (IntegerArray.isEmpty(integerArray)) {
            return;
        }
        int size = size();
        int size2 = integerArray.size() + size;
        setSize(size2);
        for (int i = 0; i < size2; i++) {
            put(size + i, integerArray.get(i));
        }
    }

    public void clear() {
        setSize(0);
    }

    public boolean contains(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            if (i == get(i2)) {
                return true;
            }
        }
        return false;
    }

    public void ensureArraySize(int i) {
        if (size() >= i) {
            return;
        }
        setSize(i);
    }

    public void fill(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            put(i2, i);
        }
    }

    public int get(int i) {
        if (i < 0 || i >= size()) {
            return 0;
        }
        return Block.getShortUnsigned(getBytesInternal(), i * 2);
    }

    public void put(int i, int i2) {
        Block.putShort(getBytesInternal(), i * 2, i2);
    }

    public void set(int[] iArr) {
        if (IntegerArray.isEmpty(iArr)) {
            setSize(0);
            return;
        }
        int length = iArr.length;
        setSize(length);
        for (int i = 0; i < length; i++) {
            put(i, iArr[i]);
        }
    }

    public void setSize(int i) {
        if (i < 0) {
            i = 0;
        }
        setBytesLength(i * 2);
    }

    public int size() {
        return getBytesLength() / 2;
    }

    public int[] toArray() {
        return IntegerArray.toArray(this);
    }

    public String toString() {
        return IntegerArray.toString(this);
    }

    public void add(int i) {
        int size = size();
        setSize(size + 1);
        put(size, i);
    }

    public void add(int[] iArr) {
        if (IntegerArray.isEmpty(iArr)) {
            return;
        }
        int size = size();
        int length = iArr.length + size;
        setSize(length);
        for (int i = 0; i < length; i++) {
            put(size + i, iArr[i]);
        }
    }
}
