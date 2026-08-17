package org.eclipse.jdt.internal.compiler.parser;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class RecoveryScannerData {
    public boolean[] insertedTokenUsed;
    public int[][] insertedTokens;
    public int[] insertedTokensPosition;
    public boolean[] removedTokenUsed;
    public int[] removedTokensEnd;
    public int[] removedTokensStart;
    public boolean[] replacedTokenUsed;
    public int[][] replacedTokens;
    public int[] replacedTokensEnd;
    public int[] replacedTokensStart;
    public int insertedTokensPtr = -1;
    public int replacedTokensPtr = -1;
    public int removedTokensPtr = -1;

    public RecoveryScannerData removeUnused() {
        int i = -1;
        if (this.insertedTokens != null) {
            int i2 = -1;
            for (int i3 = 0; i3 <= this.insertedTokensPtr; i3++) {
                boolean[] zArr = this.insertedTokenUsed;
                boolean z = zArr[i3];
                if (z) {
                    i2++;
                    int[][] iArr = this.insertedTokens;
                    iArr[i2] = iArr[i3];
                    int[] iArr2 = this.insertedTokensPosition;
                    iArr2[i2] = iArr2[i3];
                    zArr[i2] = z;
                }
            }
            this.insertedTokensPtr = i2;
        }
        if (this.replacedTokens != null) {
            int i4 = -1;
            for (int i5 = 0; i5 <= this.replacedTokensPtr; i5++) {
                boolean[] zArr2 = this.replacedTokenUsed;
                boolean z2 = zArr2[i5];
                if (z2) {
                    i4++;
                    int[][] iArr3 = this.replacedTokens;
                    iArr3[i4] = iArr3[i5];
                    int[] iArr4 = this.replacedTokensStart;
                    iArr4[i4] = iArr4[i5];
                    int[] iArr5 = this.replacedTokensEnd;
                    iArr5[i4] = iArr5[i5];
                    zArr2[i4] = z2;
                }
            }
            this.replacedTokensPtr = i4;
        }
        if (this.removedTokensStart == null) {
            return this;
        }
        for (int i6 = 0; i6 <= this.removedTokensPtr; i6++) {
            boolean[] zArr3 = this.removedTokenUsed;
            boolean z3 = zArr3[i6];
            if (z3) {
                i++;
                int[] iArr6 = this.removedTokensStart;
                iArr6[i] = iArr6[i6];
                int[] iArr7 = this.removedTokensEnd;
                iArr7[i] = iArr7[i6];
                zArr3[i] = z3;
            }
        }
        this.removedTokensPtr = i;
        return this;
    }
}
