package com.sun.org.apache.xerces.internal.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class IntStack {
    private int[] fData;
    private int fDepth;

    private void ensureCapacity(int i) {
        int[] iArr = this.fData;
        if (iArr == null) {
            this.fData = new int[32];
        } else if (iArr.length <= i) {
            int[] iArr2 = new int[iArr.length * 2];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.fData = iArr2;
        }
    }

    public void clear() {
        this.fDepth = 0;
    }

    public int elementAt(int i) {
        return this.fData[i];
    }

    public int peek() {
        return this.fData[this.fDepth - 1];
    }

    public int pop() {
        int[] iArr = this.fData;
        int i = this.fDepth - 1;
        this.fDepth = i;
        return iArr[i];
    }

    public void print() {
        System.out.print('(');
        System.out.print(this.fDepth);
        System.out.print(") {");
        for (int i = 0; i < this.fDepth; i++) {
            if (i == 3) {
                System.out.print(" ...");
                break;
            }
            System.out.print(' ');
            System.out.print(this.fData[i]);
            if (i < this.fDepth - 1) {
                System.out.print(',');
            }
        }
        System.out.print(" }");
        System.out.println();
    }

    public void push(int i) {
        ensureCapacity(this.fDepth + 1);
        int[] iArr = this.fData;
        int i2 = this.fDepth;
        this.fDepth = i2 + 1;
        iArr[i2] = i;
    }

    public int size() {
        return this.fDepth;
    }
}
