package org.bouncycastle.pqc.legacy.crypto.rainbow;

import org.bouncycastle.crypto.CipherParameters;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RainbowParameters implements CipherParameters {
    private final int[] DEFAULT_VI;
    private int[] vi;

    public RainbowParameters(int[] iArr) {
        this.DEFAULT_VI = new int[]{6, 12, 17, 22, 33};
        this.vi = iArr;
        checkParams();
    }

    private void checkParams() {
        String str;
        int[] iArr;
        int i;
        int[] iArr2 = this.vi;
        if (iArr2 == null) {
            str = "no layers defined.";
        } else if (iArr2.length > 1) {
            int i2 = 0;
            do {
                iArr = this.vi;
                if (i2 >= iArr.length - 1) {
                    return;
                }
                i = iArr[i2];
                i2++;
            } while (i < iArr[i2]);
            str = "v[i] has to be smaller than v[i+1]";
        } else {
            str = "Rainbow needs at least 1 layer, such that v1 < v2.";
        }
        w01.a(str);
    }

    public int getDocLength() {
        int[] iArr = this.vi;
        return iArr[iArr.length - 1] - iArr[0];
    }

    public int getNumOfLayers() {
        return this.vi.length - 1;
    }

    public int[] getVi() {
        return this.vi;
    }

    public RainbowParameters() {
        int[] iArr = {6, 12, 17, 22, 33};
        this.DEFAULT_VI = iArr;
        this.vi = iArr;
    }
}
