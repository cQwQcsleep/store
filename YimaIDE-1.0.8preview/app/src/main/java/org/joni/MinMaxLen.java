package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
final class MinMaxLen {
    static final int INFINITE_DISTANCE = Integer.MAX_VALUE;
    private static final short[] distValues = {1000, 500, 333, 250, 200, 167, 143, 125, 111, 100, 91, 83, 77, 71, 67, 63, 59, 56, 53, 50, 48, 45, 43, 42, 40, 38, 37, 36, 34, 33, 32, 31, 30, 29, 29, 28, 27, 26, 26, 25, 24, 24, 23, 23, 22, 22, 21, 21, 20, 20, 20, 19, 19, 19, 18, 18, 18, 17, 17, 17, 16, 16, 16, 16, 15, 15, 15, 15, 14, 14, 14, 14, 14, 14, 13, 13, 13, 13, 13, 13, 12, 12, 12, 12, 12, 12, 11, 11, 11, 11, 11, 11, 11, 11, 11, 10, 10, 10, 10, 10};
    int max;
    int min;

    public static int distanceAdd(int i, int i2) {
        if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE || i > Integer.MAX_VALUE - i2) {
            return Integer.MAX_VALUE;
        }
        return i + i2;
    }

    public static int distanceMultiply(int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i < Integer.MAX_VALUE / i2) {
            return i * i2;
        }
        return Integer.MAX_VALUE;
    }

    public static String distanceRangeToString(int i, int i2) {
        String str;
        if (i == Integer.MAX_VALUE) {
            str = "inf";
        } else {
            str = "(" + i + ")";
        }
        String strConcat = str.concat("-");
        if (i2 == Integer.MAX_VALUE) {
            return strConcat.concat("inf");
        }
        return strConcat + "(" + i2 + ")";
    }

    public void add(MinMaxLen minMaxLen) {
        this.min = distanceAdd(this.min, minMaxLen.min);
        this.max = distanceAdd(this.max, minMaxLen.max);
    }

    public void addLength(int i) {
        this.min = distanceAdd(this.min, i);
        this.max = distanceAdd(this.max, i);
    }

    public void altMerge(MinMaxLen minMaxLen) {
        int i = this.min;
        int i2 = minMaxLen.min;
        if (i > i2) {
            this.min = i2;
        }
        int i3 = this.max;
        int i4 = minMaxLen.max;
        if (i3 < i4) {
            this.max = i4;
        }
    }

    public void clear() {
        this.max = 0;
        this.min = 0;
    }

    public int compareDistanceValue(MinMaxLen minMaxLen, int i, int i2) {
        int iDistanceValue;
        int iDistanceValue2;
        if (i2 <= 0) {
            return -1;
        }
        if (i <= 0 || (iDistanceValue2 = i2 * minMaxLen.distanceValue()) > (iDistanceValue = i * distanceValue())) {
            return 1;
        }
        if (iDistanceValue2 < iDistanceValue) {
            return -1;
        }
        return Integer.compare(this.min, minMaxLen.min);
    }

    public void copy(MinMaxLen minMaxLen) {
        this.min = minMaxLen.min;
        this.max = minMaxLen.max;
    }

    public int distanceValue() {
        int i = this.max;
        if (i == Integer.MAX_VALUE) {
            return 0;
        }
        int i2 = i - this.min;
        short[] sArr = distValues;
        if (i2 < sArr.length) {
            return sArr[i2];
        }
        return 1;
    }

    public boolean equal(MinMaxLen minMaxLen) {
        return this.min == minMaxLen.min && this.max == minMaxLen.max;
    }

    public void set(int i, int i2) {
        this.min = i;
        this.max = i2;
    }
}
