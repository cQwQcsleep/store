package io.github.rosemoe.sora.text;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ComposingText {
    public int endIndex;
    public boolean preSetComposing;
    public int startIndex;

    public void adjustLength(int i) {
        this.endIndex = this.startIndex + i;
    }

    public boolean isComposing() {
        if (this.preSetComposing) {
            return true;
        }
        return this.startIndex >= 0 && this.endIndex >= 0;
    }

    public void reset() {
        this.endIndex = -1;
        this.startIndex = -1;
        this.preSetComposing = false;
    }

    public void set(int i, int i2) {
        this.startIndex = i;
        this.endIndex = i2;
    }

    public void shiftOnDelete(int i, int i2) {
        int i3 = i2 - i;
        int iMax = Math.max(i, this.startIndex);
        int iMin = Math.min(i2, this.endIndex);
        if (iMin <= iMax) {
            int i4 = this.startIndex;
            if (i4 >= i2) {
                this.startIndex = i4 - i3;
                this.endIndex -= i3;
                return;
            }
            return;
        }
        int i5 = this.endIndex - (iMin - iMax);
        this.endIndex = i5;
        int i6 = this.startIndex;
        if (i6 > i) {
            int i7 = i6 - i;
            this.startIndex = i6 - i7;
            this.endIndex = i5 - i7;
        }
    }

    public void shiftOnInsert(int i, int i2) {
        int i3;
        int i4 = i2 - i;
        int i5 = this.startIndex;
        if (i5 <= i && (i3 = this.endIndex) >= i) {
            this.endIndex = i3 + i4;
        }
        if (i5 > i) {
            this.startIndex = i5 + i4;
            this.endIndex += i4;
        }
    }
}
