package org.eclipse.tm4e.core.internal.oniguruma.impl.joni;

import org.eclipse.tm4e.core.internal.oniguruma.OnigResult;
import org.joni.Region;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class JoniOnigResult implements OnigResult {
    private int indexInScanner;
    private final Region region;

    public JoniOnigResult(Region region, int i) {
        this.region = region;
        this.indexInScanner = i;
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int count() {
        return this.region.getNumRegs();
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int getIndexOfRegex() {
        return this.indexInScanner;
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int lengthAt(int i) {
        int end = this.region.getEnd(i) - this.region.getBeg(i);
        if (end > 0) {
            return end;
        }
        return 0;
    }

    @Override // org.eclipse.tm4e.core.internal.oniguruma.OnigResult
    public int locationAt(int i) {
        int beg = this.region.getBeg(i);
        if (beg > 0) {
            return beg;
        }
        return 0;
    }

    public void setIndex(int i) {
        this.indexInScanner = i;
    }

    public String toString() {
        return "OnigResult [indexInScanner=" + this.indexInScanner + ", region=" + this.region + "]";
    }
}
