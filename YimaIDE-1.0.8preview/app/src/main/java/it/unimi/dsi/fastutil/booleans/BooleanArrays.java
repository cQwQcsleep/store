package it.unimi.dsi.fastutil.booleans;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class BooleanArrays {
    public static final boolean[] EMPTY_ARRAY = new boolean[0];
    public static final boolean[] DEFAULT_EMPTY_ARRAY = new boolean[0];
    public static final Hash.Strategy<boolean[]> HASH_STRATEGY = new ArrayHashStrategy();

    public static void ensureOffsetLength(boolean[] zArr, int i, int i2) {
        Arrays.ensureOffsetLength(zArr.length, i, i2);
    }

    private static void insertionSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            boolean z = zArr[i4];
            boolean z2 = zArr[i3];
            int i5 = i4;
            while (booleanComparator.compare(z, z2) < 0) {
                zArr[i5] = z2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                int i6 = i5 - 1;
                boolean z3 = zArr[i5 - 2];
                i5 = i6;
                z2 = z3;
            }
            zArr[i5] = z;
            i3 = i4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(boolean[] zArr, int i, int i2, int i3, BooleanComparator booleanComparator) {
        int iCompare = booleanComparator.compare(zArr[i], zArr[i2]);
        int iCompare2 = booleanComparator.compare(zArr[i], zArr[i3]);
        int iCompare3 = booleanComparator.compare(zArr[i2], zArr[i3]);
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

    public static void mergeSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator, boolean[] zArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(zArr, i, i2, booleanComparator);
            return;
        }
        if (zArr2 == null) {
            zArr2 = java.util.Arrays.copyOf(zArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(zArr2, i, i4, booleanComparator, zArr);
        mergeSort(zArr2, i4, i2, booleanComparator, zArr);
        if (booleanComparator.compare(zArr2[i4 - 1], zArr2[i4]) <= 0) {
            System.arraycopy(zArr2, i, zArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && booleanComparator.compare(zArr2[i5], zArr2[i6]) <= 0)) {
                zArr[i] = zArr2[i5];
                i5++;
            } else {
                zArr[i] = zArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void quickSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(zArr, i, i2, booleanComparator);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(zArr, i, i + i5, i + i6, booleanComparator);
            iMed5 = med3(zArr, iMed5 - i5, iMed5, iMed5 + i5, booleanComparator);
            iMed4 = med3(zArr, i4 - i6, i4 - i5, i4, booleanComparator);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        boolean z = zArr[med3(zArr, iMed3, iMed5, iMed4, booleanComparator)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = booleanComparator.compare(zArr[i7], z);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(zArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = booleanComparator.compare(zArr[i4], z);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(zArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(zArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(zArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(zArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(zArr, i, i11 + i, booleanComparator);
        }
        if (i12 > 1) {
            quickSort(zArr, i2 - i12, i2, booleanComparator);
        }
    }

    private static void selectionSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (booleanComparator.compare(zArr[i5], zArr[i4]) < 0) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                boolean z = zArr[i];
                zArr[i] = zArr[i4];
                zArr[i4] = z;
            }
            i = i3;
        }
    }

    public static void stableSort(boolean[] zArr, BooleanComparator booleanComparator) {
        stableSort(zArr, 0, zArr.length, booleanComparator);
    }

    public static void swap(boolean[] zArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swap(zArr, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static void unstableSort(boolean[] zArr) {
        unstableSort(zArr, 0, zArr.length);
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<boolean[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(boolean[] zArr) {
            return java.util.Arrays.hashCode(zArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(boolean[] zArr, boolean[] zArr2) {
            return java.util.Arrays.equals(zArr, zArr2);
        }
    }

    public static void stableSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator) {
        mergeSort(zArr, i, i2, booleanComparator);
    }

    public static void unstableSort(boolean[] zArr, int i, int i2) {
        quickSort(zArr, i, i2);
    }

    public static void unstableSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator) {
        quickSort(zArr, i, i2, booleanComparator);
    }

    public static void unstableSort(boolean[] zArr, BooleanComparator booleanComparator) {
        unstableSort(zArr, 0, zArr.length, booleanComparator);
    }

    public static void swap(boolean[] zArr, int i, int i2) {
        boolean z = zArr[i];
        zArr[i] = zArr[i2];
        zArr[i2] = z;
    }

    private static void selectionSort(boolean[] zArr, int i, int i2) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (!zArr[i5] && zArr[i4]) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                boolean z = zArr[i];
                zArr[i] = zArr[i4];
                zArr[i4] = z;
            }
            i = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(boolean[] zArr, int i, int i2, int i3) {
        int iCompare = Boolean.compare(zArr[i], zArr[i2]);
        int iCompare2 = Boolean.compare(zArr[i], zArr[i3]);
        int iCompare3 = Boolean.compare(zArr[i2], zArr[i3]);
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

    public static void mergeSort(boolean[] zArr, int i, int i2, BooleanComparator booleanComparator) {
        mergeSort(zArr, i, i2, booleanComparator, null);
    }

    public static void quickSort(boolean[] zArr, int i, int i2) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(zArr, i, i2);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(zArr, i, i + i5, i + i6);
            iMed5 = med3(zArr, iMed5 - i5, iMed5, iMed5 + i5);
            iMed4 = med3(zArr, i4 - i6, i4 - i5, i4);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        boolean z = zArr[med3(zArr, iMed3, iMed5, iMed4)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = Boolean.compare(zArr[i7], z);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(zArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = Boolean.compare(zArr[i4], z);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(zArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(zArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(zArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(zArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(zArr, i, i11 + i);
        }
        if (i12 > 1) {
            quickSort(zArr, i2 - i12, i2);
        }
    }
}
