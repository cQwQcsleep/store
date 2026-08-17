package it.unimi.dsi.fastutil.shorts;

import io.github.rosemoe.sora.widget.CodeEditor;
import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ShortArrays {
    public static final short[] EMPTY_ARRAY = new short[0];
    public static final short[] DEFAULT_EMPTY_ARRAY = new short[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<short[]> HASH_STRATEGY = new ArrayHashStrategy();

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

    public static void ensureOffsetLength(short[] sArr, int i, int i2) {
        Arrays.ensureOffsetLength(sArr.length, i, i2);
    }

    private static void insertionSort(short[] sArr, int i, int i2, ShortComparator shortComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            short s = sArr[i4];
            short s2 = sArr[i3];
            int i5 = i4;
            while (shortComparator.compare(s, s2) < 0) {
                sArr[i5] = s2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                int i6 = i5 - 1;
                short s3 = sArr[i5 - 2];
                i5 = i6;
                s2 = s3;
            }
            sArr[i5] = s;
            i3 = i4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(short[] sArr, int i, int i2, int i3, ShortComparator shortComparator) {
        int iCompare = shortComparator.compare(sArr[i], sArr[i2]);
        int iCompare2 = shortComparator.compare(sArr[i], sArr[i3]);
        int iCompare3 = shortComparator.compare(sArr[i2], sArr[i3]);
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

    public static void mergeSort(short[] sArr, int i, int i2, ShortComparator shortComparator, short[] sArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(sArr, i, i2, shortComparator);
            return;
        }
        if (sArr2 == null) {
            sArr2 = java.util.Arrays.copyOf(sArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(sArr2, i, i4, shortComparator, sArr);
        mergeSort(sArr2, i4, i2, shortComparator, sArr);
        if (shortComparator.compare(sArr2[i4 - 1], sArr2[i4]) <= 0) {
            System.arraycopy(sArr2, i, sArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && shortComparator.compare(sArr2[i5], sArr2[i6]) <= 0)) {
                sArr[i] = sArr2[i5];
                i5++;
            } else {
                sArr[i] = sArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void quickSort(short[] sArr, int i, int i2, ShortComparator shortComparator) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(sArr, i, i2, shortComparator);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(sArr, i, i + i5, i + i6, shortComparator);
            iMed5 = med3(sArr, iMed5 - i5, iMed5, iMed5 + i5, shortComparator);
            iMed4 = med3(sArr, i4 - i6, i4 - i5, i4, shortComparator);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        short s = sArr[med3(sArr, iMed3, iMed5, iMed4, shortComparator)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = shortComparator.compare(sArr[i7], s);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(sArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = shortComparator.compare(sArr[i4], s);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(sArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(sArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(sArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(sArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(sArr, i, i11 + i, shortComparator);
        }
        if (i12 > 1) {
            quickSort(sArr, i2 - i12, i2, shortComparator);
        }
    }

    public static void radixSort(short[] sArr, int i, int i2) {
        int i3;
        int i4 = i2 - i;
        if (i4 < 1024) {
            quickSort(sArr, i, i2);
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
            int i11 = i10 % 2;
            int i12 = i11 == 0 ? CodeEditor.FLAG_DRAW_SOFT_WRAP : i6;
            int i13 = (1 - i11) * 8;
            int i14 = i9 + i8;
            int i15 = i6;
            int i16 = i14;
            while (true) {
                int i17 = i16 - 1;
                if (i16 == i8) {
                    break;
                }
                int i18 = ((sArr[i17] >>> i13) & 255) ^ i12;
                iArr4[i18] = iArr4[i18] + 1;
                i16 = i17;
            }
            int i19 = -1;
            int i20 = i8;
            for (int i21 = i15; i21 < i5; i21++) {
                int i22 = iArr4[i21];
                if (i22 != 0) {
                    i19 = i21;
                }
                i20 += i22;
                iArr5[i21] = i20;
            }
            int i23 = i14 - iArr4[i19];
            while (i8 <= i23) {
                short s = sArr[i8];
                int i24 = ((s >>> i13) & 255) ^ i12;
                if (i8 < i23) {
                    while (true) {
                        int i25 = iArr5[i24] - 1;
                        iArr5[i24] = i25;
                        if (i25 <= i8) {
                            break;
                        }
                        short s2 = sArr[i25];
                        sArr[i25] = s;
                        i24 = ((s2 >>> i13) & 255) ^ i12;
                        s = s2;
                    }
                    sArr[i8] = s;
                }
                if (i10 < 1 && (i3 = iArr4[i24]) > 1) {
                    if (i3 < 1024) {
                        quickSort(sArr, i8, i3 + i8);
                    } else {
                        iArr[i7] = i8;
                        iArr2[i7] = iArr4[i24];
                        iArr3[i7] = i10 + 1;
                        i7++;
                    }
                }
                i8 += iArr4[i24];
                iArr4[i24] = i15;
                i5 = 256;
            }
            i6 = i15;
        }
    }

    private static void selectionSort(short[] sArr, int i, int i2, ShortComparator shortComparator) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (shortComparator.compare(sArr[i5], sArr[i4]) < 0) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                short s = sArr[i];
                sArr[i] = sArr[i4];
                sArr[i4] = s;
            }
            i = i3;
        }
    }

    public static void stableSort(short[] sArr, ShortComparator shortComparator) {
        stableSort(sArr, 0, sArr.length, shortComparator);
    }

    public static void swap(short[] sArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swap(sArr, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static void unstableSort(short[] sArr, int i, int i2) {
        if (i2 - i >= 1000) {
            radixSort(sArr, i, i2);
        } else {
            quickSort(sArr, i, i2);
        }
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<short[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(short[] sArr) {
            return java.util.Arrays.hashCode(sArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(short[] sArr, short[] sArr2) {
            return java.util.Arrays.equals(sArr, sArr2);
        }
    }

    public static void stableSort(short[] sArr, int i, int i2, ShortComparator shortComparator) {
        mergeSort(sArr, i, i2, shortComparator);
    }

    public static void swap(short[] sArr, int i, int i2) {
        short s = sArr[i];
        sArr[i] = sArr[i2];
        sArr[i2] = s;
    }

    public static void unstableSort(short[] sArr) {
        unstableSort(sArr, 0, sArr.length);
    }

    public static void unstableSort(short[] sArr, int i, int i2, ShortComparator shortComparator) {
        quickSort(sArr, i, i2, shortComparator);
    }

    public static void unstableSort(short[] sArr, ShortComparator shortComparator) {
        unstableSort(sArr, 0, sArr.length, shortComparator);
    }

    private static void selectionSort(short[] sArr, int i, int i2) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (sArr[i5] < sArr[i4]) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                short s = sArr[i];
                sArr[i] = sArr[i4];
                sArr[i4] = s;
            }
            i = i3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(short[] sArr, int i, int i2, int i3) {
        int iCompare = Short.compare(sArr[i], sArr[i2]);
        int iCompare2 = Short.compare(sArr[i], sArr[i3]);
        int iCompare3 = Short.compare(sArr[i2], sArr[i3]);
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

    public static void mergeSort(short[] sArr, int i, int i2, ShortComparator shortComparator) {
        mergeSort(sArr, i, i2, shortComparator, null);
    }

    public static void quickSort(short[] sArr, int i, int i2) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(sArr, i, i2);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(sArr, i, i + i5, i + i6);
            iMed5 = med3(sArr, iMed5 - i5, iMed5, iMed5 + i5);
            iMed4 = med3(sArr, i4 - i6, i4 - i5, i4);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        short s = sArr[med3(sArr, iMed3, iMed5, iMed4)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = Short.compare(sArr[i7], s);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(sArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = Short.compare(sArr[i4], s);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(sArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(sArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(sArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(sArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(sArr, i, i11 + i);
        }
        if (i12 > 1) {
            quickSort(sArr, i2 - i12, i2);
        }
    }
}
