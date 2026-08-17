package org.eclipse.jdt.internal.compiler.codegen;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class FloatCache {
    private int elementSize;
    private float[] keyTable;
    private int[] valueTable;

    public FloatCache(int i) {
        this.elementSize = 0;
        this.keyTable = new float[i];
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
                this.keyTable[length] = 0.0f;
                this.valueTable[length] = 0;
            }
        }
    }

    public boolean containsKey(float f) {
        int i = this.elementSize;
        if (f == 0.0f) {
            for (int i2 = 0; i2 < i; i2++) {
                if (this.keyTable[i2] == 0.0f) {
                    int iFloatToIntBits = Float.floatToIntBits(f);
                    int iFloatToIntBits2 = Float.floatToIntBits(this.keyTable[i2]);
                    if (iFloatToIntBits == Integer.MIN_VALUE && iFloatToIntBits2 == Integer.MIN_VALUE) {
                        return true;
                    }
                    if (iFloatToIntBits == 0 && iFloatToIntBits2 == 0) {
                        return true;
                    }
                }
            }
        } else {
            for (int i3 = 0; i3 < i; i3++) {
                if (this.keyTable[i3] == f) {
                    return true;
                }
            }
        }
        return false;
    }

    public int put(float f, int i) {
        int i2 = this.elementSize;
        float[] fArr = this.keyTable;
        if (i2 == fArr.length) {
            float[] fArr2 = new float[i2 * 2];
            this.keyTable = fArr2;
            System.arraycopy(fArr, 0, fArr2, 0, i2);
            int[] iArr = this.valueTable;
            int i3 = this.elementSize;
            int[] iArr2 = new int[i3 * 2];
            this.valueTable = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, i3);
        }
        float[] fArr3 = this.keyTable;
        int i4 = this.elementSize;
        fArr3[i4] = f;
        this.valueTable[i4] = i;
        this.elementSize = i4 + 1;
        return i;
    }

    public int putIfAbsent(float f, int i) {
        int i2 = this.elementSize;
        if (f == 0.0f) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (this.keyTable[i3] == 0.0f) {
                    int iFloatToIntBits = Float.floatToIntBits(f);
                    int iFloatToIntBits2 = Float.floatToIntBits(this.keyTable[i3]);
                    if (iFloatToIntBits == Integer.MIN_VALUE && iFloatToIntBits2 == Integer.MIN_VALUE) {
                        return this.valueTable[i3];
                    }
                    if (iFloatToIntBits == 0 && iFloatToIntBits2 == 0) {
                        return this.valueTable[i3];
                    }
                }
            }
        } else {
            for (int i4 = 0; i4 < i2; i4++) {
                if (this.keyTable[i4] == f) {
                    return this.valueTable[i4];
                }
            }
        }
        int i5 = this.elementSize;
        float[] fArr = this.keyTable;
        if (i5 == fArr.length) {
            float[] fArr2 = new float[i5 * 2];
            this.keyTable = fArr2;
            System.arraycopy(fArr, 0, fArr2, 0, i5);
            int[] iArr = this.valueTable;
            int i6 = this.elementSize;
            int[] iArr2 = new int[i6 * 2];
            this.valueTable = iArr2;
            System.arraycopy(iArr, 0, iArr2, 0, i6);
        }
        float[] fArr3 = this.keyTable;
        int i7 = this.elementSize;
        fArr3[i7] = f;
        this.valueTable[i7] = i;
        this.elementSize = i7 + 1;
        return -i;
    }

    public String toString() {
        int i = this.elementSize;
        StringBuilder sb = new StringBuilder("{");
        for (int i2 = 0; i2 < i; i2++) {
            float f = this.keyTable[i2];
            if (f != 0.0f || (f == 0.0f && this.valueTable[i2] != 0)) {
                sb.append(f);
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

    public FloatCache() {
        this(13);
    }
}
