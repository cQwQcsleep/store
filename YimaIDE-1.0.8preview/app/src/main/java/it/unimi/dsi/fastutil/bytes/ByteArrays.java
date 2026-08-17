package it.unimi.dsi.fastutil.bytes;

import it.unimi.dsi.fastutil.Arrays;
import it.unimi.dsi.fastutil.Hash;
import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ByteArrays {
    public static final byte[] EMPTY_ARRAY = new byte[0];
    public static final byte[] DEFAULT_EMPTY_ARRAY = new byte[0];
    protected static final Segment POISON_PILL = new Segment(-1, -1, -1);
    public static final Hash.Strategy<byte[]> HASH_STRATEGY = new ArrayHashStrategy();

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

    public static void ensureOffsetLength(byte[] bArr, int i, int i2) {
        Arrays.ensureOffsetLength(bArr.length, i, i2);
    }

    public static byte[] forceCapacity(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i2);
        return bArr2;
    }

    private static void insertionSort(byte[] bArr, int i, int i2, ByteComparator byteComparator) {
        int i3 = i;
        while (true) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return;
            }
            byte b = bArr[i4];
            byte b2 = bArr[i3];
            int i5 = i4;
            while (byteComparator.compare(b, b2) < 0) {
                bArr[i5] = b2;
                if (i == i5 - 1) {
                    i5--;
                    break;
                }
                int i6 = i5 - 1;
                byte b3 = bArr[i5 - 2];
                i5 = i6;
                b2 = b3;
            }
            bArr[i5] = b;
            i3 = i4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0025 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:12:0x0026 A[RETURN] */
    private static int med3(byte[] bArr, int i, int i2, int i3, ByteComparator byteComparator) {
        int iCompare = byteComparator.compare(bArr[i], bArr[i2]);
        int iCompare2 = byteComparator.compare(bArr[i], bArr[i3]);
        int iCompare3 = byteComparator.compare(bArr[i2], bArr[i3]);
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

    public static void mergeSort(byte[] bArr, int i, int i2, ByteComparator byteComparator, byte[] bArr2) {
        int i3 = i2 - i;
        if (i3 < 16) {
            insertionSort(bArr, i, i2, byteComparator);
            return;
        }
        if (bArr2 == null) {
            bArr2 = java.util.Arrays.copyOf(bArr, i2);
        }
        int i4 = (i + i2) >>> 1;
        mergeSort(bArr2, i, i4, byteComparator, bArr);
        mergeSort(bArr2, i4, i2, byteComparator, bArr);
        if (byteComparator.compare(bArr2[i4 - 1], bArr2[i4]) <= 0) {
            System.arraycopy(bArr2, i, bArr, i, i3);
            return;
        }
        int i5 = i;
        int i6 = i4;
        while (i < i2) {
            if (i6 >= i2 || (i5 < i4 && byteComparator.compare(bArr2[i5], bArr2[i6]) <= 0)) {
                bArr[i] = bArr2[i5];
                i5++;
            } else {
                bArr[i] = bArr2[i6];
                i6++;
            }
            i++;
        }
    }

    public static void quickSort(byte[] bArr, int i, int i2, ByteComparator byteComparator) {
        int iMed3;
        int iMed4;
        int i3 = i2 - i;
        if (i3 < 16) {
            selectionSort(bArr, i, i2, byteComparator);
            return;
        }
        int iMed5 = (i3 / 2) + i;
        int i4 = i2 - 1;
        if (i3 > 128) {
            int i5 = i3 / 8;
            int i6 = i5 * 2;
            iMed3 = med3(bArr, i, i + i5, i + i6, byteComparator);
            iMed5 = med3(bArr, iMed5 - i5, iMed5, iMed5 + i5, byteComparator);
            iMed4 = med3(bArr, i4 - i6, i4 - i5, i4, byteComparator);
        } else {
            iMed3 = i;
            iMed4 = i4;
        }
        byte b = bArr[med3(bArr, iMed3, iMed5, iMed4, byteComparator)];
        int i7 = i;
        int i8 = i7;
        int i9 = i4;
        while (true) {
            if (i7 <= i4) {
                int iCompare = byteComparator.compare(bArr[i7], b);
                if (iCompare <= 0) {
                    if (iCompare == 0) {
                        swap(bArr, i8, i7);
                        i8++;
                    }
                    i7++;
                }
            }
            while (i4 >= i7) {
                int iCompare2 = byteComparator.compare(bArr[i4], b);
                if (iCompare2 < 0) {
                    break;
                }
                if (iCompare2 == 0) {
                    swap(bArr, i4, i9);
                    i9--;
                }
                i4--;
            }
            if (i7 > i4) {
                break;
            }
            swap(bArr, i7, i4);
            i7++;
            i4--;
        }
        int i10 = i8 - i;
        int i11 = i7 - i8;
        int iMin = Math.min(i10, i11);
        swap(bArr, i, i7 - iMin, iMin);
        int i12 = i9 - i4;
        int iMin2 = Math.min(i12, (i2 - i9) - 1);
        swap(bArr, i7, i2 - iMin2, iMin2);
        if (i11 > 1) {
            quickSort(bArr, i, i11 + i, byteComparator);
        }
        if (i12 > 1) {
            quickSort(bArr, i2 - i12, i2, byteComparator);
        }
    }

    private static void selectionSort(byte[] bArr, int i, int i2, ByteComparator byteComparator) {
        while (i < i2 - 1) {
            int i3 = i + 1;
            int i4 = i;
            for (int i5 = i3; i5 < i2; i5++) {
                if (byteComparator.compare(bArr[i5], bArr[i4]) < 0) {
                    i4 = i5;
                }
            }
            if (i4 != i) {
                byte b = bArr[i];
                bArr[i] = bArr[i4];
                bArr[i4] = b;
            }
            i = i3;
        }
    }

    public static void stableSort(byte[] bArr, ByteComparator byteComparator) {
        stableSort(bArr, 0, bArr.length, byteComparator);
    }

    public static void swap(byte[] bArr, int i, int i2, int i3) {
        int i4 = 0;
        while (i4 < i3) {
            swap(bArr, i, i2);
            i4++;
            i++;
            i2++;
        }
    }

    public static void unstableSort(byte[] bArr) {
        unstableSort(bArr, 0, bArr.length);
    }

    public static final class ArrayHashStrategy implements Hash.Strategy<byte[]>, Serializable {
        private static final long serialVersionUID = -7046029254386353129L;

        private ArrayHashStrategy() {
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public int hashCode(byte[] bArr) {
            return java.util.Arrays.hashCode(bArr);
        }

        @Override // it.unimi.dsi.fastutil.Hash.Strategy
        public boolean equals(byte[] bArr, byte[] bArr2) {
            return java.util.Arrays.equals(bArr, bArr2);
        }
    }

    public static void stableSort(byte[] bArr, int i, int i2, ByteComparator byteComparator) {
        mergeSort(bArr, i, i2, byteComparator);
    }

    public static void unstableSort(byte[] bArr, int i, int i2) {
        java.util.Arrays.sort(bArr, i, i2);
    }

    public static void stableSort(byte[] bArr, int i, int i2) {
        unstableSort(bArr, i, i2);
    }

    public static void unstableSort(byte[] bArr, int i, int i2, ByteComparator byteComparator) {
        quickSort(bArr, i, i2, byteComparator);
    }

    public static void unstableSort(byte[] bArr, ByteComparator byteComparator) {
        unstableSort(bArr, 0, bArr.length, byteComparator);
    }

    public static void swap(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    public static void mergeSort(byte[] bArr, int i, int i2, ByteComparator byteComparator) {
        mergeSort(bArr, i, i2, byteComparator, null);
    }
}
