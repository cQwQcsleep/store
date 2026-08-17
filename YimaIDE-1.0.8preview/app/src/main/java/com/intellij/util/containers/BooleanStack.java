package com.intellij.util.containers;

import com.intellij.util.ArrayUtil;
import java.util.Arrays;
import java.util.EmptyStackException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class BooleanStack {
    private boolean[] data;
    private int size;

    public BooleanStack(int i) {
        this.data = new boolean[i];
        this.size = 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BooleanStack)) {
            return false;
        }
        BooleanStack booleanStack = (BooleanStack) obj;
        if (this.size != booleanStack.size) {
            return false;
        }
        for (int i = 0; i < booleanStack.size; i++) {
            if (this.data[i] != booleanStack.data[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean pop() {
        int i = this.size;
        if (i == 0) {
            throw new EmptyStackException();
        }
        boolean[] zArr = this.data;
        int i2 = i - 1;
        this.size = i2;
        return zArr[i2];
    }

    public void push(boolean z) {
        int i = this.size;
        boolean[] zArr = this.data;
        if (i >= zArr.length) {
            this.data = ArrayUtil.realloc(zArr, (zArr.length * 3) / 2);
        }
        boolean[] zArr2 = this.data;
        int i2 = this.size;
        this.size = i2 + 1;
        zArr2[i2] = z;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOf(this.data, this.size));
    }

    public BooleanStack() {
        this(5);
    }
}
