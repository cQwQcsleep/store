package it.unimi.dsi.fastutil.longs;

import io.github.rosemoe.sora.widget.CodeEditor;
import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class LongArrays {
    public static final long[] EMPTY_ARRAY = new long[0];
    public static final long[] DEFAULT_EMPTY_ARRAY = new long[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<long[]> HASH_STRATEGY = new ArrayHashStrategy();

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

    public static void ensureOffsetLength(long[] jArr, int i, int i2) {
        Arrays.ensureOffsetLength(jArr.length, i, i2);
    }

    public static long[] forceCapacity(long[] jArr, int i, int i2) {
        long[] jArr2 = new long[i];
        System.arraycopy(jArr, 0, jArr2, 0, i2);
        return jArr2;
    }

    private static void insertionSort(long[] jArr, int i, int i2, LongComparator longComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            long j = jArr[i4];
            long j2 = jArr[i3];
            int i5 = i4;
            while (longComparator.compare(j, j2) < 0) {
                jArr[i5] = j2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                long j3 = jArr[i5 - 2];
                i5--;
                j2 = j3;
            }
            jArr[i5] = j;
            i3 = i4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(long[] jArr, int i, int i2, int i3, LongComparator longComparator) {
        int iCompare = longComparator.compare(jArr[i], jArr[i2]);
        int iCompare2 = longComparator.compare(jArr[i], jArr[i3]);
        int iCompare3 = longComparator.compare(jArr[i2], jArr[i3]);
        if (iCompare < 0) {
            if (iCompare3 >= 0) {
                if (iCompare2 < 0) {
                    return i3;
                }
                return i;
            }
            return i2;
        }
        if (iCompare3 <= 0) {
            if (iCompare2 > 0) {
                return i3;
            }
            return i;
        }
        return i2;
    }

    public static void mergeSort(long[] jArr, int i, int i2, LongComparator longComparator, long[] jArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(jArr, i, i2, longComparator);
            return;
        }
        if (jArr2 == null) {
            jArr2 = java.util.Arrays.copyOf(jArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(jArr2, i, i4, longComparator, jArr);
        mergeSort(jArr2, i4, i2, longComparator, jArr);
        if (longComparator.compare(jArr2[i4 - 1], jArr2[i4]) <= 0) {
            System.arraycopy(jArr2, i, jArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && longComparator.compare(jArr2[i5], jArr2[i6]) <= 0)) {
                jArr[i] = jArr2[i5];
                i5++;
            } else {
                jArr[i] = jArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void quickSort(long[] jArr, int i, int i2, LongComparator longComparator) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(jArr, i, i2, longComparator);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(jArr, i, i + i5, i + i6, longComparator);
            iMed5 = med3(jArr, iMed5 - i5, iMed5, iMed5 + i5, longComparator);
            iMed4 = med3(jArr, i4 - i6, i4 - i5, i4, longComparator);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        long j = jArr[med3(jArr, iMed3, iMed5, iMed4, longComparator)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = longComparator.compare(jArr[i7], j);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(jArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = longComparator.compare(jArr[i4], j);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(jArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(jArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i7 - i8;
        int iMin = Math.min(i8 - i, i10);
        swap(jArr, i, i7 - iMin, iMin);
        int i11 = i9 - i4;
        int iMin2 = Math.min(i11, (i2 - i9) - 1);
        swap(jArr, i7, i2 - iMin2, iMin2);
        if (i10 > 1) {
            quickSort(jArr, i, i10 + i, longComparator);
        }
        if (i11 > 1) {
            quickSort(jArr, i2 - i11, i2, longComparator);
        }
    }

    public static void radixSort(long[] jArr, int i, int i2) {
        int i3;
        int i4 = i2 - i;
        if (i4 < 1024) {
            quickSort(jArr, i, i2);
            return;
        }
        int[] iArr = new int[1786];
        int[] iArr2 = new int[1786];
        int[] iArr3 = new int[1786];
        int i5 = 0;
        iArr[0] = i;
        iArr2[0] = i4;
        iArr3[0] = 0;
        int i6 = 256;
        int[] iArr4 = new int[256];
        int[] iArr5 = new int[256];
        int i7 = 1;
        while (i7 > 0) {
            i7--;
            int i8 = iArr[i7];
            int i9 = iArr2[i7];
            int i10 = iArr3[i7];
            int i11 = i10 % 8;
            int i12 = i11 == 0 ? CodeEditor.FLAG_DRAW_SOFT_WRAP : i5;
            int i13 = (7 - i11) * 8;
            int i14 = i9 + i8;
            int i15 = i5;
            int i16 = i14;
            while (true) {
                int i17 = i16 - 1;
                if (i16 == i8) {
                    break;
                }
                int i18 = (int) (((jArr[i17] >>> i13) & 255) ^ ((long) i12));
                iArr4[i18] = iArr4[i18] + 1;
                i16 = i17;
                iArr3 = iArr3;
            }
            int[] iArr6 = iArr3;
            int i19 = -1;
            int i20 = i8;
            for (int i21 = i15; i21 < i6; i21++) {
                int i22 = iArr4[i21];
                if (i22 != 0) {
                    i19 = i21;
                }
                i20 += i22;
                iArr5[i21] = i20;
            }
            int i23 = i14 - iArr4[i19];
            while (i8 <= i23) {
                long j = jArr[i8];
                long j2 = j;
                long j3 = i12;
                int[] iArr7 = iArr4;
                int i24 = (int) (((j >>> i13) & 255) ^ j3);
                if (i8 < i23) {
                    while (true) {
                        int i25 = iArr5[i24] - 1;
                        iArr5[i24] = i25;
                        if (i25 <= i8) {
                            break;
                        }
                        long j4 = jArr[i25];
                        jArr[i25] = j2;
                        i24 = (int) (((j4 >>> i13) & 255) ^ j3);
                        j2 = j4;
                    }
                    jArr[i8] = j2;
                }
                if (i10 < 7 && (i3 = iArr7[i24]) > 1) {
                    if (i3 < 1024) {
                        quickSort(jArr, i8, i3 + i8);
                    } else {
                        iArr[i7] = i8;
                        iArr2[i7] = iArr7[i24];
                        iArr6[i7] = i10 + 1;
                        i7++;
                    }
                }
                i8 += iArr7[i24];
                iArr7[i24] = i15;
                iArr4 = iArr7;
                i6 = 256;
            }
            i5 = i15;
            iArr3 = iArr6;
        }
    }

    private static void selectionSort(long[] jArr, int i, int i2, LongComparator longComparator) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (longComparator.compare(jArr[i5], jArr[i4]) < 0) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                long j = jArr[i];
                jArr[i] = jArr[i4];
                jArr[i4] = j;
            }
            i = i3;
        }
    }

    public static void stableSort(long[] jArr, LongComparator longComparator) {
        stableSort(jArr, 0, jArr.length, longComparator);
    }

    public static void swap(long[] jArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swap(jArr, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static void unstableSort(long[] jArr, int i, int i2) {
        if (i2 - i >= 4000) {
            radixSort(jArr, i, i2);
        } else {
            quickSort(jArr, i, i2);
        }
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<long[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(long[] jArr) {
            return java.util.Arrays.hashCode(jArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(long[] jArr, long[] jArr2) {
            return java.util.Arrays.equals(jArr, jArr2);
        }
    }

    public static void stableSort(long[] jArr, int i, int i2, LongComparator longComparator) {
        mergeSort(jArr, i, i2, longComparator);
    }

    public static void stableSort(long[] jArr, int i, int i2) {
        unstableSort(jArr, i, i2);
    }

    public static void swap(long[] jArr, int i, int i2) {
        long j = jArr[i];
        jArr[i] = jArr[i2];
        jArr[i2] = j;
    }

    public static void unstableSort(long[] jArr) {
        unstableSort(jArr, 0, jArr.length);
    }

    public static void unstableSort(long[] jArr, int i, int i2, LongComparator longComparator) {
        quickSort(jArr, i, i2, longComparator);
    }

    public static void unstableSort(long[] jArr, LongComparator longComparator) {
        unstableSort(jArr, 0, jArr.length, longComparator);
    }

    private static void selectionSort(long[] jArr, int i, int i2) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (jArr[i5] < jArr[i4]) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                long j = jArr[i];
                jArr[i] = jArr[i4];
                jArr[i4] = j;
            }
            i = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(long[] jArr, int i, int i2, int i3) {
        int iCompare = Long.compare(jArr[i], jArr[i2]);
        int iCompare2 = Long.compare(jArr[i], jArr[i3]);
        int iCompare3 = Long.compare(jArr[i2], jArr[i3]);
        if (iCompare < 0) {
            if (iCompare3 >= 0) {
                if (iCompare2 < 0) {
                    return i3;
                }
                return i;
            }
            return i2;
        }
        if (iCompare3 <= 0) {
            if (iCompare2 > 0) {
                return i3;
            }
            return i;
        }
        return i2;
    }

    public static void mergeSort(long[] jArr, int i, int i2, LongComparator longComparator) {
        mergeSort(jArr, i, i2, longComparator, null);
    }

    public static void quickSort(long[] jArr, int i, int i2) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(jArr, i, i2);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(jArr, i, i + i5, i + i6);
            iMed5 = med3(jArr, iMed5 - i5, iMed5, iMed5 + i5);
            iMed4 = med3(jArr, i4 - i6, i4 - i5, i4);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        long j = jArr[med3(jArr, iMed3, iMed5, iMed4)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = Long.compare(jArr[i7], j);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(jArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = Long.compare(jArr[i4], j);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(jArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(jArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i7 - i8;
        int iMin = Math.min(i8 - i, i10);
        swap(jArr, i, i7 - iMin, iMin);
        int i11 = i9 - i4;
        int iMin2 = Math.min(i11, (i2 - i9) - 1);
        swap(jArr, i7, i2 - iMin2, iMin2);
        if (i10 > 1) {
            quickSort(jArr, i, i10 + i);
        }
        if (i11 > 1) {
            quickSort(jArr, i2 - i11, i2);
        }
    }
}
