package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CharArrays {
    public static final char[] EMPTY_ARRAY = new char[0];
    public static final char[] DEFAULT_EMPTY_ARRAY = new char[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<char[]> HASH_STRATEGY = new ArrayHashStrategy();

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

    public static void ensureOffsetLength(char[] cArr, int i, int i2) {
        Arrays.ensureOffsetLength(cArr.length, i, i2);
    }

    private static void insertionSort(char[] cArr, int i, int i2, CharComparator charComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            char c = cArr[i4];
            char c2 = cArr[i3];
            int i5 = i4;
            while (charComparator.compare(c, c2) < 0) {
                cArr[i5] = c2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                int i6 = i5 - 1;
                char c3 = cArr[i5 - 2];
                i5 = i6;
                c2 = c3;
            }
            cArr[i5] = c;
            i3 = i4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(char[] cArr, int i, int i2, int i3, CharComparator charComparator) {
        int iCompare = charComparator.compare(cArr[i], cArr[i2]);
        int iCompare2 = charComparator.compare(cArr[i], cArr[i3]);
        int iCompare3 = charComparator.compare(cArr[i2], cArr[i3]);
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

    public static void mergeSort(char[] cArr, int i, int i2, CharComparator charComparator, char[] cArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(cArr, i, i2, charComparator);
            return;
        }
        if (cArr2 == null) {
            cArr2 = java.util.Arrays.copyOf(cArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(cArr2, i, i4, charComparator, cArr);
        mergeSort(cArr2, i4, i2, charComparator, cArr);
        if (charComparator.compare(cArr2[i4 - 1], cArr2[i4]) <= 0) {
            System.arraycopy(cArr2, i, cArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && charComparator.compare(cArr2[i5], cArr2[i6]) <= 0)) {
                cArr[i] = cArr2[i5];
                i5++;
            } else {
                cArr[i] = cArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void quickSort(char[] cArr, int i, int i2, CharComparator charComparator) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(cArr, i, i2, charComparator);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(cArr, i, i + i5, i + i6, charComparator);
            iMed5 = med3(cArr, iMed5 - i5, iMed5, iMed5 + i5, charComparator);
            iMed4 = med3(cArr, i4 - i6, i4 - i5, i4, charComparator);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        char c = cArr[med3(cArr, iMed3, iMed5, iMed4, charComparator)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = charComparator.compare(cArr[i7], c);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(cArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = charComparator.compare(cArr[i4], c);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(cArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(cArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(cArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(cArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(cArr, i, i11 + i, charComparator);
        }
        if (i12 > 1) {
            quickSort(cArr, i2 - i12, i2, charComparator);
        }
    }

    public static void radixSort(char[] cArr, int i, int i2) {
        int i3;
        int i4 = i2 - i;
        if (i4 < 1024) {
            quickSort(cArr, i, i2);
            return;
        }
        int i5 = 256;
        int[] iArr = new int[256];
        int[] iArr2 = new int[256];
        int[] iArr3 = new int[256];
        int i6 = 0;
        iArr[0] = i;
        iArr2[0] = i4;
        iArr3[0] = 0;
        int[] iArr4 = new int[256];
        int[] iArr5 = new int[256];
        int i7 = 1;
        while (i7 > 0) {
            i7--;
            int i8 = iArr[i7];
            int i9 = iArr2[i7];
            int i10 = iArr3[i7];
            int i11 = (1 - (i10 % 2)) * 8;
            int i12 = i9 + i8;
            int i13 = i12;
            while (true) {
                int i14 = i13 - 1;
                if (i13 == i8) {
                    break;
                }
                int i15 = (cArr[i14] >>> i11) & 255;
                iArr4[i15] = iArr4[i15] + 1;
                i13 = i14;
            }
            int i16 = -1;
            int i17 = i6;
            int i18 = i8;
            while (i6 < i5) {
                int i19 = iArr4[i6];
                if (i19 != 0) {
                    i16 = i6;
                }
                i18 += i19;
                iArr5[i6] = i18;
                i6++;
            }
            int i20 = i12 - iArr4[i16];
            while (i8 <= i20) {
                char c = cArr[i8];
                int i21 = (c >>> i11) & 255;
                if (i8 < i20) {
                    while (true) {
                        int i22 = iArr5[i21] - 1;
                        iArr5[i21] = i22;
                        if (i22 <= i8) {
                            break;
                        }
                        char c2 = cArr[i22];
                        cArr[i22] = c;
                        c = c2;
                        i21 = (c2 >>> i11) & 255;
                    }
                    cArr[i8] = c;
                }
                if (i10 < 1 && (i3 = iArr4[i21]) > 1) {
                    if (i3 < 1024) {
                        quickSort(cArr, i8, i3 + i8);
                    } else {
                        iArr[i7] = i8;
                        iArr2[i7] = iArr4[i21];
                        iArr3[i7] = i10 + 1;
                        i7++;
                    }
                }
                i8 += iArr4[i21];
                iArr4[i21] = i17;
                i5 = 256;
            }
            i6 = i17;
        }
    }

    private static void selectionSort(char[] cArr, int i, int i2, CharComparator charComparator) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (charComparator.compare(cArr[i5], cArr[i4]) < 0) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                char c = cArr[i];
                cArr[i] = cArr[i4];
                cArr[i4] = c;
            }
            i = i3;
        }
    }

    public static void stableSort(char[] cArr, CharComparator charComparator) {
        stableSort(cArr, 0, cArr.length, charComparator);
    }

    public static void swap(char[] cArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swap(cArr, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static void unstableSort(char[] cArr, int i, int i2) {
        if (i2 - i >= 2000) {
            radixSort(cArr, i, i2);
        } else {
            quickSort(cArr, i, i2);
        }
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<char[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(char[] cArr) {
            return java.util.Arrays.hashCode(cArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(char[] cArr, char[] cArr2) {
            return java.util.Arrays.equals(cArr, cArr2);
        }
    }

    public static void stableSort(char[] cArr, int i, int i2, CharComparator charComparator) {
        mergeSort(cArr, i, i2, charComparator);
    }

    public static void swap(char[] cArr, int i, int i2) {
        char c = cArr[i];
        cArr[i] = cArr[i2];
        cArr[i2] = c;
    }

    public static void unstableSort(char[] cArr) {
        unstableSort(cArr, 0, cArr.length);
    }

    public static void unstableSort(char[] cArr, int i, int i2, CharComparator charComparator) {
        quickSort(cArr, i, i2, charComparator);
    }

    public static void unstableSort(char[] cArr, CharComparator charComparator) {
        unstableSort(cArr, 0, cArr.length, charComparator);
    }

    private static void selectionSort(char[] cArr, int i, int i2) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (cArr[i5] < cArr[i4]) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                char c = cArr[i];
                cArr[i] = cArr[i4];
                cArr[i4] = c;
            }
            i = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(char[] cArr, int i, int i2, int i3) {
        int iCompare = Character.compare(cArr[i], cArr[i2]);
        int iCompare2 = Character.compare(cArr[i], cArr[i3]);
        int iCompare3 = Character.compare(cArr[i2], cArr[i3]);
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

    public static void mergeSort(char[] cArr, int i, int i2, CharComparator charComparator) {
        mergeSort(cArr, i, i2, charComparator, null);
    }

    public static void quickSort(char[] cArr, int i, int i2) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(cArr, i, i2);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(cArr, i, i + i5, i + i6);
            iMed5 = med3(cArr, iMed5 - i5, iMed5, iMed5 + i5);
            iMed4 = med3(cArr, i4 - i6, i4 - i5, i4);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        char c = cArr[med3(cArr, iMed3, iMed5, iMed4)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = Character.compare(cArr[i7], c);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(cArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = Character.compare(cArr[i4], c);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(cArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(cArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(cArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(cArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(cArr, i, i11 + i);
        }
        if (i12 > 1) {
            quickSort(cArr, i2 - i12, i2);
        }
    }
}
