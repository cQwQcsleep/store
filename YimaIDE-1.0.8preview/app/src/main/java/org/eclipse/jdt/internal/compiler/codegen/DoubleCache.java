package org.eclipse.jdt.internal.compiler.codegen;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class DoubleCache {
    private int elementSize;
    private double[] keyTable;
    private int[] valueTable;

    public DoubleCache(int i) {
        this.elementSize = 0;
        this.keyTable = new double[i];
        this.valueTable = new int[i];
    }

    public void clear() {
        int length = this.keyTable.length;
        while (true) {
            length--;
            if (length < 0) {
                this.elementSize = 0;
                return;
            } else {
                this.keyTable[length] = 0.0d;
                this.valueTable[length] = 0;
            }
        }
    }

    public boolean containsKey(double d) {
        int i = this.elementSize;
        if (d == 0.0d) {
            for (int i2 = 0; i2 < i; i2++) {
                if (this.keyTable[i2] == 0.0d) {
                    long jDoubleToLongBits = Double.doubleToLongBits(d);
                    long jDoubleToLongBits2 = Double.doubleToLongBits(this.keyTable[i2]);
                    if (jDoubleToLongBits == Long.MIN_VALUE && jDoubleToLongBits2 == Long.MIN_VALUE) {
                        return true;
                    }
                    if (jDoubleToLongBits == 0 && jDoubleToLongBits2 == 0) {
                        return true;
                    }
                }
            }
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                if (this.keyTable[i3] == d) {
                    return true;
                }
            }
        }
        return false;
    }

    public int put(double d, int i) {
        int i2 = this.elementSize;
        double[] dArr = this.keyTable;
        if (i2 == dArr.length) {
            double[] dArr2 = new double[i2 * 2];
            this.keyTable = dArr2;
            System.arraycopy(dArr, 0, dArr2, 0, i2);
            int[] iArr = this.valueTable;
            int i3 = this.elementSize;
            int[] iArr2 = new int[i3 * 2];
            this.valueTable = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, i3);
        }
        double[] dArr3 = this.keyTable;
        int i4 = this.elementSize;
        dArr3[i4] = d;
        this.valueTable[i4] = i;
        this.elementSize = i4 + 1;
        return i;
    }

    public int putIfAbsent(double d, int i) {
        int i2 = this.elementSize;
        if (d == 0.0d) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.keyTable[i3] == 0.0d) {
                    long jDoubleToLongBits = Double.doubleToLongBits(d);
                    long jDoubleToLongBits2 = Double.doubleToLongBits(this.keyTable[i3]);
                    if (jDoubleToLongBits == Long.MIN_VALUE && jDoubleToLongBits2 == Long.MIN_VALUE) {
                        return this.valueTable[i3];
                    }
                    if (jDoubleToLongBits == 0 && jDoubleToLongBits2 == 0) {
                        return this.valueTable[i3];
                    }
                }
            }
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.keyTable[i4] == d) {
                    return this.valueTable[i4];
                }
            }
        }
        int i5 = this.elementSize;
        double[] dArr = this.keyTable;
        if (i5 == dArr.length) {
            double[] dArr2 = new double[i5 * 2];
            this.keyTable = dArr2;
            System.arraycopy(dArr, 0, dArr2, 0, i5);
            int[] iArr = this.valueTable;
            int i6 = this.elementSize;
            int[] iArr2 = new int[i6 * 2];
            this.valueTable = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, i6);
        }
        double[] dArr3 = this.keyTable;
        int i7 = this.elementSize;
        dArr3[i7] = d;
        this.valueTable[i7] = i;
        this.elementSize = i7 + 1;
        return -i;
    }

    public String toString() {
        int i = this.elementSize;
        StringBuilder sb = new StringBuilder("{");
        for (int i2 = 0; i2 < i; i2++) {
            double d = this.keyTable[i2];
            if (d != 0.0d || (d == 0.0d && this.valueTable[i2] != 0)) {
                sb.append(d);
                sb.append("->");
                sb.append(this.valueTable[i2]);
            }
            if (i2 < i) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public DoubleCache() {
        this(13);
    }
}
