package it.unimi.dsi.fastutil.floats;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class FloatArrays {
    public static final float[] EMPTY_ARRAY = new float[0];
    public static final float[] DEFAULT_EMPTY_ARRAY = new float[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<float[]> HASH_STRATEGY = new ArrayHashStrategy();

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

    public static void ensureOffsetLength(float[] fArr, int i, int i2) {
        Arrays.ensureOffsetLength(fArr.length, i, i2);
    }

    private static void insertionSort(float[] fArr, int i, int i2, FloatComparator floatComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            float f = fArr[i4];
            float f2 = fArr[i3];
            int i5 = i4;
            while (floatComparator.compare(f, f2) < 0) {
                fArr[i5] = f2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                int i6 = i5 - 1;
                float f3 = fArr[i5 - 2];
                i5 = i6;
                f2 = f3;
            }
            fArr[i5] = f;
            i3 = i4;
        }
    }

    public static void mergeSort(float[] fArr, int i, int i2, float[] fArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(fArr, i, i2);
            return;
        }
        if (fArr2 == null) {
            fArr2 = java.util.Arrays.copyOf(fArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(fArr2, i, i4, fArr);
        mergeSort(fArr2, i4, i2, fArr);
        if (Float.compare(fArr2[i4 - 1], fArr2[i4]) <= 0) {
            System.arraycopy(fArr2, i, fArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && Float.compare(fArr2[i5], fArr2[i6]) <= 0)) {
                fArr[i] = fArr2[i5];
                i5++;
            } else {
                fArr[i] = fArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void stableSort(float[] fArr) {
        stableSort(fArr, 0, fArr.length);
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<float[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(float[] fArr) {
            return java.util.Arrays.hashCode(fArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(float[] fArr, float[] fArr2) {
            return java.util.Arrays.equals(fArr, fArr2);
        }
    }

    public static void stableSort(float[] fArr, int i, int i2) {
        mergeSort(fArr, i, i2);
    }

    public static void stableSort(float[] fArr, int i, int i2, FloatComparator floatComparator) {
        mergeSort(fArr, i, i2, floatComparator);
    }

    public static void stableSort(float[] fArr, FloatComparator floatComparator) {
        stableSort(fArr, 0, fArr.length, floatComparator);
    }

    private static void insertionSort(float[] fArr, int i, int i2) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            float f = fArr[i4];
            float f2 = fArr[i3];
            int i5 = i4;
            while (Float.compare(f, f2) < 0) {
                fArr[i5] = f2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                int i6 = i5 - 1;
                float f3 = fArr[i5 - 2];
                i5 = i6;
                f2 = f3;
            }
            fArr[i5] = f;
            i3 = i4;
        }
    }

    public static void mergeSort(float[] fArr, int i, int i2) {
        mergeSort(fArr, i, i2, (float[]) null);
    }

    public static void mergeSort(float[] fArr, int i, int i2, FloatComparator floatComparator, float[] fArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(fArr, i, i2, floatComparator);
            return;
        }
        if (fArr2 == null) {
            fArr2 = java.util.Arrays.copyOf(fArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(fArr2, i, i4, floatComparator, fArr);
        mergeSort(fArr2, i4, i2, floatComparator, fArr);
        if (floatComparator.compare(fArr2[i4 - 1], fArr2[i4]) <= 0) {
            System.arraycopy(fArr2, i, fArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 < i2 && (i5 >= i4 || floatComparator.compare(fArr2[i5], fArr2[i6]) > 0)) {
                fArr[i] = fArr2[i6];
                i6++;
            } else {
                fArr[i] = fArr2[i5];
                i5++;
            }
            i++;
        }
    }

    public static void mergeSort(float[] fArr, int i, int i2, FloatComparator floatComparator) {
        mergeSort(fArr, i, i2, floatComparator, null);
    }
}
