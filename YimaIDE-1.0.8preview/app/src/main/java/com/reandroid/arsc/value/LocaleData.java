package com.reandroid.arsc.value;

import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class LocaleData {
    static final int PACKED_ROOT = 0;
    public static final int[] ENGLISH_STOP_LIST = {1701707776, 1701741568};
    public static final char[] ENGLISH_CHARS = {'e', 'n'};
    public static final char[] LATIN_CHARS = {'L', 'a', 't', 'n'};
    public static int SCRIPT_LENGTH = 4;
    public static int US_SPANISH = 1702057299;
    public static int MEXICAN_SPANISH = 1702055256;
    public static int LATIN_AMERICAN_SPANISH = 1702077476;
    static final int SCRIPT_PARENTS_COUNT = LocaleDataTables.SCRIPT_PARENTS.length;

    public static int dropRegion(int i) {
        return i & (-65536);
    }

    public static int findAncestors(int[] iArr, int[] iArr2, int i, char[] cArr, int[] iArr3, int i2) {
        int i3 = 0;
        do {
            if (iArr != null) {
                iArr[i3] = i;
            }
            i3++;
            for (int i4 = 0; i4 < i2; i4++) {
                if (iArr3[i4] == i) {
                    iArr2[0] = i4;
                    return i3;
                }
            }
            i = findParent(i, cArr);
        } while (i != 0);
        iArr2[0] = -1;
        return i3;
    }

    public static int findDistance(int i, char[] cArr, int[] iArr, int i2) {
        int[] iArr2 = new int[1];
        return (findAncestors(null, iArr2, i, cArr, iArr, i2) + iArr2[0]) - 1;
    }

    public static int findParent(int i, char[] cArr) {
        LocaleDataTables.ScriptParent[] scriptParentArr = LocaleDataTables.SCRIPT_PARENTS;
        if (!hasRegion(i)) {
            return 0;
        }
        for (int i2 = 0; i2 < SCRIPT_PARENTS_COUNT; i2++) {
            if (Arrays.equals(cArr, scriptParentArr[i2].script)) {
                int[] iArrFind = LocaleUtil.find(scriptParentArr[i2].map, i);
                if (iArrFind == null) {
                    break;
                }
                return iArrFind[1];
            }
        }
        return dropRegion(i);
    }

    public static boolean hasRegion(int i) {
        return (i & 65535) != 0;
    }

    public static boolean isRepresentative(int i, char[] cArr) {
        return LocaleUtil.contains(LocaleDataTables.REPRESENTATIVE_LOCALES, ((long) cArr[3]) | (((long) i) << 32) | (((long) cArr[0]) << 24) | (((long) cArr[1]) << 16) | (((long) cArr[2]) << 8));
    }

    public static boolean isSpecialSpanish(int i) {
        return i == US_SPANISH || i == MEXICAN_SPANISH;
    }

    public static int localeDataCompareRegions(char[] cArr, char[] cArr2, char[] cArr3, char[] cArr4, char[] cArr5) {
        int i;
        int i2;
        if (cArr[0] == cArr2[0] && cArr[1] == cArr2[1]) {
            return 0;
        }
        int iPackLocale = packLocale(cArr3, cArr);
        int iPackLocale2 = packLocale(cArr3, cArr2);
        int iPackLocale3 = packLocale(cArr3, cArr5);
        boolean zIsSpecialSpanish = isSpecialSpanish(iPackLocale);
        boolean zIsSpecialSpanish2 = isSpecialSpanish(iPackLocale2);
        if (zIsSpecialSpanish && !zIsSpecialSpanish2 && iPackLocale2 != (i2 = LATIN_AMERICAN_SPANISH)) {
            iPackLocale = i2;
        } else if (zIsSpecialSpanish2 && !zIsSpecialSpanish && iPackLocale != (i = LATIN_AMERICAN_SPANISH)) {
            iPackLocale2 = i;
        }
        int[] iArr = new int[4];
        int[] iArr2 = new int[0];
        int iFindAncestors = findAncestors(iArr, iArr2, iPackLocale3, cArr4, new int[][]{new int[]{iPackLocale, iPackLocale2}}[0], 1);
        int i3 = iArr2[0];
        if (i3 == 0) {
            return 1;
        }
        if (i3 == 1) {
            return -1;
        }
        int iFindDistance = findDistance(iPackLocale, cArr4, iArr, iFindAncestors);
        int iFindDistance2 = findDistance(iPackLocale2, cArr4, iArr, iFindAncestors);
        if (iFindDistance != iFindDistance2) {
            return iFindDistance2 - iFindDistance;
        }
        boolean zIsRepresentative = isRepresentative(iPackLocale, cArr4);
        boolean zIsRepresentative2 = isRepresentative(iPackLocale2, cArr4);
        return zIsRepresentative != zIsRepresentative2 ? (zIsRepresentative ? 1 : 0) - (zIsRepresentative2 ? 1 : 0) : (int) ((((long) iPackLocale2) & (-4294967296L)) - ((-4294967296L) & ((long) iPackLocale)));
    }

    public static void localeDataComputeScript(char[] cArr, char[] cArr2, char[] cArr3) {
        int[] iArrFind;
        if (cArr2[0] == 0) {
            LocaleUtil.memset(cArr, (char) 0, SCRIPT_LENGTH);
            return;
        }
        int iPackLocale = packLocale(cArr2, cArr3);
        int[][] iArr = LocaleDataTables.LIKELY_SCRIPTS;
        int[] iArrFind2 = LocaleUtil.find(iArr, iPackLocale);
        if (iArrFind2 != null) {
            LocaleUtil.memcpy(cArr, LocaleDataTables.SCRIPT_CODES[iArrFind2[1]], SCRIPT_LENGTH);
        } else if (cArr3[0] == 0 || (iArrFind = LocaleUtil.find(iArr, dropRegion(iPackLocale))) == null) {
            LocaleUtil.memset(cArr, (char) 0, SCRIPT_LENGTH);
        } else {
            LocaleUtil.memcpy(cArr, LocaleDataTables.SCRIPT_CODES[iArrFind[1]], SCRIPT_LENGTH);
        }
    }

    public static boolean localeDataIsCloseToUsEnglish(char[] cArr) {
        int[] iArr = new int[1];
        findAncestors(null, iArr, packLocale(ENGLISH_CHARS, cArr), LATIN_CHARS, ENGLISH_STOP_LIST, 2);
        return iArr[0] == 0;
    }

    public static int packLocale(char[] cArr, char[] cArr2) {
        return (cArr[1] << 16) | (cArr[0] << 24) | (cArr2[0] << '\b') | cArr2[1];
    }
}
