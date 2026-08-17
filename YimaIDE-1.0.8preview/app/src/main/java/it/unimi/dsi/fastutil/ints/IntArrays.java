package it.unimi.dsi.fastutil.ints;

import io.github.rosemoe.sora.widget.CodeEditor;
import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class IntArrays {
    public static final int[] EMPTY_ARRAY = new int[0];
    public static final int[] DEFAULT_EMPTY_ARRAY = new int[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<int[]> HASH_STRATEGY = new ArrayHashStrategy();

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

    public static void ensureOffsetLength(int[] iArr, int i, int i2) {
        Arrays.ensureOffsetLength(iArr.length, i, i2);
    }

    public static int[] forceCapacity(int[] iArr, int i, int i2) {
        int[] iArr2 = new int[i];
        System.arraycopy(iArr, 0, iArr2, 0, i2);
        return iArr2;
    }

    private static void insertionSort(int[] iArr, int i, int i2, IntComparator intComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            int i5 = iArr[i4];
            int i6 = iArr[i3];
            int i7 = i4;
            while (intComparator.compare(i5, i6) < 0) {
                iArr[i7] = i6;
                if (i == i7 - 1) {
                    i7--;
                    break;
                }
                int i8 = i7 - 1;
                int i9 = iArr[i7 - 2];
                i7 = i8;
                i6 = i9;
            }
            iArr[i7] = i5;
            i3 = i4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(int[] iArr, int i, int i2, int i3, IntComparator intComparator) {
        int iCompare = intComparator.compare(iArr[i], iArr[i2]);
        int iCompare2 = intComparator.compare(iArr[i], iArr[i3]);
        int iCompare3 = intComparator.compare(iArr[i2], iArr[i3]);
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

    public static void mergeSort(int[] iArr, int i, int i2, IntComparator intComparator, int[] iArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(iArr, i, i2, intComparator);
            return;
        }
        if (iArr2 == null) {
            iArr2 = java.util.Arrays.copyOf(iArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(iArr2, i, i4, intComparator, iArr);
        mergeSort(iArr2, i4, i2, intComparator, iArr);
        if (intComparator.compare(iArr2[i4 - 1], iArr2[i4]) <= 0) {
            System.arraycopy(iArr2, i, iArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && intComparator.compare(iArr2[i5], iArr2[i6]) <= 0)) {
                iArr[i] = iArr2[i5];
                i5++;
            } else {
                iArr[i] = iArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void quickSort(int[] iArr, int i, int i2, IntComparator intComparator) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(iArr, i, i2, intComparator);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(iArr, i, i + i5, i + i6, intComparator);
            iMed5 = med3(iArr, iMed5 - i5, iMed5, iMed5 + i5, intComparator);
            iMed4 = med3(iArr, i4 - i6, i4 - i5, i4, intComparator);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        int i7 = iArr[med3(iArr, iMed3, iMed5, iMed4, intComparator)];
        int i8 = i;
        int i9 = i8;
        int i10 = i4;
        while (true) {
            if (i8 <= i4) {
                int iCompare = intComparator.compare(iArr[i8], i7);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(iArr, i9, i8);
                        i9++;
                    }
                    i8++;
                }
            }
            while (i4 >= i8) {
                int iCompare2 = intComparator.compare(iArr[i4], i7);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(iArr, i4, i10);
                    i10--;
                }
                i4--;
            }
            if (i8 > i4) {
                break;
            }
            swap(iArr, i8, i4);
            i8++;
            i4--;
        }
        int i11 = i9 - i;
        int i12 = i8 - i9;
        int iMin = Math.min(i11, i12);
        swap(iArr, i, i8 - iMin, iMin);
        int i13 = i10 - i4;
        int iMin2 = Math.min(i13, (i2 - i10) - 1);
        swap(iArr, i8, i2 - iMin2, iMin2);
        if (i12 > 1) {
            quickSort(iArr, i, i12 + i, intComparator);
        }
        if (i13 > 1) {
            quickSort(iArr, i2 - i13, i2, intComparator);
        }
    }

    public static void radixSort(int[] iArr, int i, int i2) {
        int i3;
        int i4 = i2 - i;
        if (i4 < 1024) {
            quickSort(iArr, i, i2);
            return;
        }
        int[] iArr2 = new int[766];
        int[] iArr3 = new int[766];
        int[] iArr4 = new int[766];
        int i5 = 0;
        iArr2[0] = i;
        iArr3[0] = i4;
        iArr4[0] = 0;
        int i6 = 256;
        int[] iArr5 = new int[256];
        int[] iArr6 = new int[256];
        int i7 = 1;
        while (i7 > 0) {
            i7--;
            int i8 = iArr2[i7];
            int i9 = iArr3[i7];
            int i10 = iArr4[i7];
            int i11 = i10 % 4;
            int i12 = i11 == 0 ? CodeEditor.FLAG_DRAW_SOFT_WRAP : i5;
            int i13 = (3 - i11) * 8;
            int i14 = i9 + i8;
            int i15 = i5;
            int i16 = i14;
            while (true) {
                int i17 = i16 - 1;
                if (i16 == i8) {
                    break;
                }
                int i18 = ((iArr[i17] >>> i13) & 255) ^ i12;
                iArr5[i18] = iArr5[i18] + 1;
                i16 = i17;
            }
            int i19 = -1;
            int i20 = i8;
            for (int i21 = i15; i21 < i6; i21++) {
                int i22 = iArr5[i21];
                if (i22 != 0) {
                    i19 = i21;
                }
                i20 += i22;
                iArr6[i21] = i20;
            }
            int i23 = i14 - iArr5[i19];
            while (i8 <= i23) {
                int i24 = iArr[i8];
                int i25 = ((i24 >>> i13) & 255) ^ i12;
                if (i8 < i23) {
                    while (true) {
                        int i26 = iArr6[i25] - 1;
                        iArr6[i25] = i26;
                        if (i26 <= i8) {
                            break;
                        }
                        int i27 = iArr[i26];
                        iArr[i26] = i24;
                        i24 = i27;
                        i25 = ((i27 >>> i13) & 255) ^ i12;
                    }
                    iArr[i8] = i24;
                }
                if (i10 < 3 && (i3 = iArr5[i25]) > 1) {
                    if (i3 < 1024) {
                        quickSort(iArr, i8, i3 + i8);
                    } else {
                        iArr2[i7] = i8;
                        iArr3[i7] = iArr5[i25];
                        iArr4[i7] = i10 + 1;
                        i7++;
                    }
                }
                i8 += iArr5[i25];
                iArr5[i25] = i15;
                i6 = 256;
            }
            i5 = i15;
        }
    }

    private static void selectionSort(int[] iArr, int i, int i2, IntComparator intComparator) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (intComparator.compare(iArr[i5], iArr[i4]) < 0) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                int i6 = iArr[i];
                iArr[i] = iArr[i4];
                iArr[i4] = i6;
            }
            i = i3;
        }
    }

    public static void stableSort(int[] iArr, IntComparator intComparator) {
        stableSort(iArr, 0, iArr.length, intComparator);
    }

    public static void swap(int[] iArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swap(iArr, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static void unstableSort(int[] iArr, int i, int i2) {
        if (i2 - i >= 2000) {
            radixSort(iArr, i, i2);
        } else {
            quickSort(iArr, i, i2);
        }
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<int[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(int[] iArr) {
            return java.util.Arrays.hashCode(iArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(int[] iArr, int[] iArr2) {
            return java.util.Arrays.equals(iArr, iArr2);
        }
    }

    public static void stableSort(int[] iArr, int i, int i2, IntComparator intComparator) {
        mergeSort(iArr, i, i2, intComparator);
    }

    public static void stableSort(int[] iArr, int i, int i2) {
        unstableSort(iArr, i, i2);
    }

    public static void swap(int[] iArr, int i, int i2) {
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
    }

    public static void unstableSort(int[] iArr) {
        unstableSort(iArr, 0, iArr.length);
    }

    public static void unstableSort(int[] iArr, int i, int i2, IntComparator intComparator) {
        quickSort(iArr, i, i2, intComparator);
    }

    public static void unstableSort(int[] iArr, IntComparator intComparator) {
        unstableSort(iArr, 0, iArr.length, intComparator);
    }

    private static void selectionSort(int[] iArr, int i, int i2) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (iArr[i5] < iArr[i4]) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                int i6 = iArr[i];
                iArr[i] = iArr[i4];
                iArr[i4] = i6;
            }
            i = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(int[] iArr, int i, int i2, int i3) {
        int iCompare = Integer.compare(iArr[i], iArr[i2]);
        int iCompare2 = Integer.compare(iArr[i], iArr[i3]);
        int iCompare3 = Integer.compare(iArr[i2], iArr[i3]);
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

    public static void mergeSort(int[] iArr, int i, int i2, IntComparator intComparator) {
        mergeSort(iArr, i, i2, intComparator, null);
    }

    public static void quickSort(int[] iArr, int i, int i2) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(iArr, i, i2);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(iArr, i, i + i5, i + i6);
            iMed5 = med3(iArr, iMed5 - i5, iMed5, iMed5 + i5);
            iMed4 = med3(iArr, i4 - i6, i4 - i5, i4);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        int i7 = iArr[med3(iArr, iMed3, iMed5, iMed4)];
        int i8 = i;
        int i9 = i8;
        int i10 = i4;
        while (true) {
            if (i8 <= i4) {
                int iCompare = Integer.compare(iArr[i8], i7);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(iArr, i9, i8);
                        i9++;
                    }
                    i8++;
                }
            }
            while (i4 >= i8) {
                int iCompare2 = Integer.compare(iArr[i4], i7);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(iArr, i4, i10);
                    i10--;
                }
                i4--;
            }
            if (i8 > i4) {
                break;
            }
            swap(iArr, i8, i4);
            i8++;
            i4--;
        }
        int i11 = i9 - i;
        int i12 = i8 - i9;
        int iMin = Math.min(i11, i12);
        swap(iArr, i, i8 - iMin, iMin);
        int i13 = i10 - i4;
        int iMin2 = Math.min(i13, (i2 - i10) - 1);
        swap(iArr, i8, i2 - iMin2, iMin2);
        if (i12 > 1) {
            quickSort(iArr, i, i12 + i);
        }
        if (i13 > 1) {
            quickSort(iArr, i2 - i13, i2);
        }
    }
}
