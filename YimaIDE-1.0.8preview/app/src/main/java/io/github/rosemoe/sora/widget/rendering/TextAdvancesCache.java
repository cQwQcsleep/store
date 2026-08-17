package io.github.rosemoe.sora.widget.rendering;

import defpackage.qf1;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TextAdvancesCache {
    private static final int BLOCK_SIZE = 262144;
    private final float[][] cache;
    private final int size;

    public TextAdvancesCache(int i) {
        if (i < 0) {
            qf1.a("invalid size: ", i);
            throw null;
        }
        this.size = i;
        int i2 = (262143 + i) / BLOCK_SIZE;
        this.cache = new float[i2][];
        int i3 = 0;
        while (i3 < i2) {
            int i4 = i2 - 1;
            this.cache[i3] = new float[(i3 == i4 ? i - (i4 * BLOCK_SIZE) : BLOCK_SIZE) + 1];
            i3++;
        }
    }

    public void finishBuilding() {
        for (float[] fArr : this.cache) {
            float f = fArr[0];
            fArr[0] = 0.0f;
            int i = 1;
            while (i <= fArr.length - 1) {
                float f2 = fArr[i];
                fArr[i] = fArr[i - 1] + f;
                i++;
                f = f2;
            }
        }
    }

    public float getAdvanceAt(int i) {
        int i2 = i / BLOCK_SIZE;
        int i3 = i % BLOCK_SIZE;
        float[] fArr = this.cache[i2];
        return fArr[i3 + 1] - fArr[i3];
    }

    public float getAdvancesSum(int i, int i2) {
        float[][] fArr = this.cache;
        if (fArr.length == 1) {
            float[] fArr2 = fArr[0];
            return fArr2[i2] - fArr2[i];
        }
        int i3 = i / BLOCK_SIZE;
        int i4 = i2 / BLOCK_SIZE;
        float f = 0.0f;
        while (i3 <= i4) {
            int i5 = i3 * BLOCK_SIZE;
            int i6 = i3 + 1;
            int iMin = Math.min(i6 * BLOCK_SIZE, this.size);
            int iMax = Math.max(i, i5);
            int iMin2 = Math.min(i2, iMin);
            if (iMax < iMin2) {
                float[] fArr3 = this.cache[i3];
                f += fArr3[iMin2 - i5] - fArr3[iMax - i5];
            }
            i3 = i6;
        }
        return f;
    }

    public int getSize() {
        return this.size;
    }

    public void setAdvanceAt(int i, float f) {
        int i2 = i / BLOCK_SIZE;
        this.cache[i2][i % BLOCK_SIZE] = f;
    }
}
