package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import java.lang.reflect.Array;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class CaseInsensitiveMap {
    private static final int CHUNK_MASK = 1023;
    private static final int CHUNK_SHIFT = 10;
    private static final int CHUNK_SIZE = 1024;
    private static final int INITIAL_CHUNK_COUNT = 64;
    private static final int LOWER_CASE_MATCH = 1;
    private static final int UPPER_CASE_MATCH = 2;
    private static int[][][] caseInsensitiveMap;

    static {
        buildCaseInsensitiveMap();
    }

    private static void buildCaseInsensitiveMap() {
        int i;
        caseInsensitiveMap = (int[][][]) Array.newInstance((Class<?>) int[].class, 64, 1024);
        for (int i2 = 0; i2 < 65536; i2++) {
            int lowerCase = Character.toLowerCase(i2);
            int upperCase = Character.toUpperCase(i2);
            if (lowerCase != upperCase || lowerCase != i2) {
                int[] iArrUpdateMap = new int[2];
                if (lowerCase != i2) {
                    iArrUpdateMap[0] = lowerCase;
                    iArrUpdateMap[1] = 1;
                    int[] mapping = getMapping(lowerCase);
                    if (mapping != null) {
                        iArrUpdateMap = updateMap(i2, iArrUpdateMap, lowerCase, mapping, 1);
                    }
                    i = 2;
                } else {
                    i = 0;
                }
                if (upperCase != i2) {
                    if (i == iArrUpdateMap.length) {
                        iArrUpdateMap = expandMap(iArrUpdateMap, 2);
                    }
                    iArrUpdateMap[i] = upperCase;
                    iArrUpdateMap[i + 1] = 2;
                    int[] mapping2 = getMapping(upperCase);
                    if (mapping2 != null) {
                        iArrUpdateMap = updateMap(i2, iArrUpdateMap, upperCase, mapping2, 2);
                    }
                }
                set(i2, iArrUpdateMap);
            }
        }
    }

    private static boolean contains(int[] iArr, int i, int i2) {
        for (int i3 = 0; i3 < iArr.length; i3 += 2) {
            if (iArr[i3] == i && iArr[i3 + 1] == i2) {
                return true;
            }
        }
        return false;
    }

    private static int[] expandAndAdd(int[] iArr, int i, int i2) {
        int length = iArr.length;
        int[] iArr2 = new int[length + 2];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        iArr2[length] = i;
        iArr2[length + 1] = i2;
        return iArr2;
    }

    private static int[] expandMap(int[] iArr, int i) {
        int length = iArr.length;
        int[] iArr2 = new int[i + length];
        System.arraycopy(iArr, 0, iArr2, 0, length);
        return iArr2;
    }

    public static int[] get(int i) {
        if (i < 65536) {
            return getMapping(i);
        }
        return null;
    }

    private static int[] getMapping(int i) {
        return caseInsensitiveMap[i >>> 10][i & 1023];
    }

    private static void set(int i, int[] iArr) {
        caseInsensitiveMap[i >>> 10][i & 1023] = iArr;
    }

    private static int[] updateMap(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        for (int i4 = 0; i4 < iArr2.length; i4 += 2) {
            int i5 = iArr2[i4];
            int[] mapping = getMapping(i5);
            if (mapping != null && contains(mapping, i2, i3)) {
                if (!contains(mapping, i)) {
                    set(i5, expandAndAdd(mapping, i, i3));
                }
                if (!contains(iArr, i5)) {
                    iArr = expandAndAdd(iArr, i5, i3);
                }
            }
        }
        if (!contains(iArr2, i)) {
            set(i2, expandAndAdd(iArr2, i, i3));
        }
        return iArr;
    }

    private static boolean contains(int[] iArr, int i) {
        for (int i2 = 0; i2 < iArr.length; i2 += 2) {
            if (iArr[i2] == i) {
                return true;
            }
        }
        return false;
    }
}
