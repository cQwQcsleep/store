package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class RegionIterator {
    protected int endIndex;
    private final int max;
    private final boolean[] pointerStates;
    private final int[] pointers;
    private final RegionProvider[] providers;
    protected int startIndex;

    public interface RegionProvider {
        int getPointAt(int i);

        int getPointCount();
    }

    public RegionIterator(int i, RegionProvider... regionProviderArr) {
        this.max = i;
        this.providers = regionProviderArr;
        this.pointers = new int[regionProviderArr.length];
        this.pointerStates = new boolean[regionProviderArr.length];
    }

    public int getEndIndex() {
        return Math.min(this.endIndex, this.max);
    }

    public int getMax() {
        return this.max;
    }

    public int getPointer(int i) {
        return this.pointers[i];
    }

    public int getPointerValue(int i, int i2) {
        RegionProvider regionProvider = this.providers[i];
        if (i2 < 0) {
            return 0;
        }
        return i2 >= regionProvider.getPointCount() ? this.max : Math.min(regionProvider.getPointAt(i2), this.max);
    }

    public int getRegionSourcePointer(int i) {
        int pointAt = this.pointers[i] < this.providers[i].getPointCount() ? this.providers[i].getPointAt(i) : this.max;
        return ((this.endIndex > pointAt || pointAt >= this.max) && !this.pointerStates[i]) ? this.pointers[i] : this.pointers[i] - 1;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public boolean hasNextRegion() {
        return this.endIndex < this.max;
    }

    public void nextRegion() {
        int pointAt;
        this.startIndex = this.endIndex;
        int iMin = this.max;
        int i = 0;
        while (true) {
            RegionProvider[] regionProviderArr = this.providers;
            if (i >= regionProviderArr.length) {
                break;
            }
            if (this.pointers[i] >= regionProviderArr[i].getPointCount() || (pointAt = this.providers[i].getPointAt(this.pointers[i])) > this.max) {
                pointAt = this.max;
            }
            iMin = Math.min(pointAt, iMin);
            i++;
        }
        this.endIndex = iMin;
        int i2 = 0;
        while (true) {
            RegionProvider[] regionProviderArr2 = this.providers;
            if (i2 >= regionProviderArr2.length) {
                return;
            }
            if (this.pointers[i2] >= regionProviderArr2[i2].getPointCount() || this.providers[i2].getPointAt(this.pointers[i2]) != iMin) {
                this.pointerStates[i2] = false;
            } else {
                int[] iArr = this.pointers;
                iArr[i2] = iArr[i2] + 1;
                this.pointerStates[i2] = true;
            }
            i2++;
        }
    }
}
