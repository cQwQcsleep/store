package com.reandroid.utils.collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Sorter {
    private boolean sorted;

    private void runSort(int i, int i2) {
        int iCompareToMid;
        int iCompareToMid2;
        if (i2 <= i) {
            return;
        }
        int i3 = ((i2 - i) / 2) + i;
        setMid(i3);
        if (i == i3) {
            if (compareToMid(i2) < 0) {
                onSwap(i, i2);
                this.sorted = true;
                return;
            }
            return;
        }
        int i4 = i;
        int i5 = i2;
        while (i4 <= i5) {
            while (true) {
                iCompareToMid = compareToMid(i4);
                if (iCompareToMid >= 0) {
                    break;
                } else {
                    i4++;
                }
            }
            while (true) {
                iCompareToMid2 = compareToMid(i5);
                if (iCompareToMid2 <= 0) {
                    break;
                } else {
                    i5--;
                }
            }
            if (i4 <= i5) {
                if (i4 != i5 && (iCompareToMid > 0 || iCompareToMid2 < 0)) {
                    onSwap(i4, i5);
                    this.sorted = true;
                }
                i4++;
                i5--;
            }
        }
        if (i < i5) {
            runSort(i, i5);
        }
        if (i4 < i2) {
            runSort(i4, i2);
        }
    }

    public abstract int compareToMid(int i);

    public abstract void onSwap(int i, int i2);

    public abstract void setMid(int i);

    public boolean sort(int i, int i2) {
        this.sorted = false;
        runSort(i, i2 - 1);
        return this.sorted;
    }
}
