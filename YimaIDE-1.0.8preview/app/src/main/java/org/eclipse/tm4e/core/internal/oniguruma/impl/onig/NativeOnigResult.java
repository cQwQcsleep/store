package org.eclipse.tm4e.core.internal.oniguruma.impl.onig;

import org.eclipse.tm4e.core.internal.oniguruma.OnigResult;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class NativeOnigResult implements OnigResult {
    int index;
    private final int[] ranges;

    public NativeOnigResult(int[] iArr, boolean z) {
        this.index = -1;
        this.ranges = iArr;
        if (z) {
            this.index = iArr[iArr.length - 1];
        }
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int count() {
        return this.ranges.length / 2;
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int getIndexOfRegex() {
        return this.index;
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int lengthAt(int i) {
        int[] iArr = this.ranges;
        int i2 = i * 2;
        return Math.max(0, iArr[i2 + 1] - iArr[i2]);
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int locationAt(int i) {
        return Math.max(0, this.ranges[i * 2]);
    }

    public NativeOnigResult(int[] iArr) {
        this(iArr, false);
    }
}
