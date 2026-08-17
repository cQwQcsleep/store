package it.unimi.dsi.fastutil.doubles;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class DoubleArrays {
    public static final double[] EMPTY_ARRAY = new double[0];
    public static final double[] DEFAULT_EMPTY_ARRAY = new double[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<double[]> HASH_STRATEGY = new ArrayHashStrategy();

    public static final class Segment {
        protected final int length;
        protected final int level;
        protected final int offset;

        public Segment(int i, int i2, int i3) {
            this.offset = i;
            this.length = i2;
            this.level = i3;
        }

        public String toString() {
            return "Segment [offset=" + this.offset + ", length=" + this.length + ", level=" + this.level + "]";
        }
    }

    public static void ensureOffsetLength(double[] dArr, int i, int i2) {
        Arrays.ensureOffsetLength(dArr.length, i, i2);
    }

    public static double[] forceCapacity(double[] dArr, int i, int i2) {
        double[] dArr2 = new double[i];
        System.arraycopy(dArr, 0, dArr2, 0, i2);
        return dArr2;
    }

    private static void insertionSort(double[] dArr, int i, int i2, DoubleComparator doubleComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            double d = dArr[i4];
            double d2 = dArr[i3];
            int i5 = i4;
            while (doubleComparator.compare(d, d2) < 0) {
                dArr[i5] = d2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                double d3 = dArr[i5 - 2];
                i5--;
                d2 = d3;
            }
            dArr[i5] = d;
            i3 = i4;
        }
    }

    public static void mergeSort(double[] dArr, int i, int i2, double[] dArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(dArr, i, i2);
            return;
        }
        if (dArr2 == null) {
            dArr2 = java.util.Arrays.copyOf(dArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(dArr2, i, i4, dArr);
        mergeSort(dArr2, i4, i2, dArr);
        if (Double.compare(dArr2[i4 - 1], dArr2[i4]) <= 0) {
            System.arraycopy(dArr2, i, dArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && Double.compare(dArr2[i5], dArr2[i6]) <= 0)) {
                dArr[i] = dArr2[i5];
                i5++;
            } else {
                dArr[i] = dArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void stableSort(double[] dArr) {
        stableSort(dArr, 0, dArr.length);
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<double[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(double[] dArr) {
            return java.util.Arrays.hashCode(dArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(double[] dArr, double[] dArr2) {
            return java.util.Arrays.equals(dArr, dArr2);
        }
    }

    public static void stableSort(double[] dArr, int i, int i2) {
        mergeSort(dArr, i, i2);
    }

    public static void stableSort(double[] dArr, int i, int i2, DoubleComparator doubleComparator) {
        mergeSort(dArr, i, i2, doubleComparator);
    }

    public static void stableSort(double[] dArr, DoubleComparator doubleComparator) {
        stableSort(dArr, 0, dArr.length, doubleComparator);
    }

    private static void insertionSort(double[] dArr, int i, int i2) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            double d = dArr[i4];
            double d2 = dArr[i3];
            int i5 = i4;
            while (Double.compare(d, d2) < 0) {
                dArr[i5] = d2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                double d3 = dArr[i5 - 2];
                i5--;
                d2 = d3;
            }
            dArr[i5] = d;
            i3 = i4;
        }
    }

    public static void mergeSort(double[] dArr, int i, int i2) {
        mergeSort(dArr, i, i2, (double[]) null);
    }

    public static void mergeSort(double[] dArr, int i, int i2, DoubleComparator doubleComparator, double[] dArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(dArr, i, i2, doubleComparator);
            return;
        }
        if (dArr2 == null) {
            dArr2 = java.util.Arrays.copyOf(dArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(dArr2, i, i4, doubleComparator, dArr);
        mergeSort(dArr2, i4, i2, doubleComparator, dArr);
        if (doubleComparator.compare(dArr2[i4 - 1], dArr2[i4]) <= 0) {
            System.arraycopy(dArr2, i, dArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 < i2 && (i5 >= i4 || doubleComparator.compare(dArr2[i5], dArr2[i6]) > 0)) {
                dArr[i] = dArr2[i6];
                i6++;
            } else {
                dArr[i] = dArr2[i5];
                i5++;
            }
            i++;
        }
    }

    public static void mergeSort(double[] dArr, int i, int i2, DoubleComparator doubleComparator) {
        mergeSort(dArr, i, i2, doubleComparator, null);
    }
}
