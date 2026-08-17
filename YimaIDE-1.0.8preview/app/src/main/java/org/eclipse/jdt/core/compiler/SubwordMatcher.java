package org.eclipse.jdt.core.compiler;

import java.util.Arrays;
import java.util.BitSet;
import org.eclipse.jdt.internal.compiler.parser.ScannerHelper;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
class SubwordMatcher {
    private static final int[] EMPTY_REGIONS = new int[0];
    private final char[] name;
    private final BitSet wordBoundaries;

    public enum Case {
        SEPARATOR,
        LOWER,
        UPPER;

        /* JADX INFO: renamed from: values, reason: to resolve conflict with enum method */
        public static Case[] valuesCustom() {
            Case[] caseArrValuesCustom = values();
            int length = caseArrValuesCustom.length;
            Case[] caseArr = new Case[length];
            System.arraycopy(caseArrValuesCustom, 0, caseArr, 0, length);
            return caseArr;
        }
    }

    public SubwordMatcher(String str) {
        this.name = str.toCharArray();
        this.wordBoundaries = new BitSet(str.length());
        int i = 0;
        while (i < this.name.length) {
            int i2 = i + 1;
            if (isWordBoundary(caseAt(i - 1), caseAt(i), caseAt(i2))) {
                this.wordBoundaries.set(i);
            }
            i = i2;
        }
    }

    private Case caseAt(int i) {
        if (i >= 0) {
            char[] cArr = this.name;
            if (i < cArr.length) {
                char c = cArr[i];
                if (c == '_') {
                    return Case.SEPARATOR;
                }
                return ScannerHelper.isUpperCase(c) ? Case.UPPER : Case.LOWER;
            }
        }
        return Case.SEPARATOR;
    }

    private boolean equalsIgnoreCase(char c, char c2) {
        return ScannerHelper.toLowerCase(c) == ScannerHelper.toLowerCase(c2);
    }

    private int indexOfWordStart(int i, char c) {
        while (true) {
            char[] cArr = this.name;
            if (i >= cArr.length) {
                return -1;
            }
            char c2 = cArr[i];
            if (isWordBoundary(i) && equalsIgnoreCase(c2, c)) {
                return i;
            }
            if (!ScannerHelper.isJavaIdentifierPart(c2)) {
                return -1;
            }
            i++;
        }
    }

    private static boolean isWordBoundary(Case r3, Case r4, Case r5) {
        Case r6;
        if (r3 == r4 && r4 == r5) {
            return false;
        }
        if (r3 == Case.SEPARATOR) {
            return true;
        }
        return r4 == Case.UPPER && (r3 == (r6 = Case.LOWER) || r5 == r6);
    }

    public int[] getMatchingRegions(String str) {
        int iIndexOfWordStart;
        int[] iArrCopyOfRange = EMPTY_REGIONS;
        int i = -1;
        int i2 = 0;
        int iIndexOfWordStart2 = 0;
        int i3 = 0;
        while (i2 < str.length()) {
            i++;
            if (i == this.name.length) {
                return null;
            }
            char cCharAt = str.charAt(i2);
            char c = this.name[i];
            if (cCharAt != c && (isWordBoundary(i) || !equalsIgnoreCase(cCharAt, c))) {
                if (i > iIndexOfWordStart2) {
                    iArrCopyOfRange = Arrays.copyOf(iArrCopyOfRange, iArrCopyOfRange.length + 2);
                    iArrCopyOfRange[iArrCopyOfRange.length - 2] = iIndexOfWordStart2;
                    iArrCopyOfRange[iArrCopyOfRange.length - 1] = i - iIndexOfWordStart2;
                }
                iIndexOfWordStart2 = indexOfWordStart(i, cCharAt);
                if (iIndexOfWordStart2 < 0 && (iIndexOfWordStart = indexOfWordStart(i, str.charAt(i3))) > 0) {
                    iArrCopyOfRange = Arrays.copyOfRange(iArrCopyOfRange, 0, iArrCopyOfRange.length - 2);
                    iIndexOfWordStart2 = iIndexOfWordStart;
                    i2 = i3;
                }
                if (iIndexOfWordStart2 < 0) {
                    return null;
                }
                i3 = i2;
                i = iIndexOfWordStart2;
            }
            i2++;
        }
        int[] iArrCopyOf = Arrays.copyOf(iArrCopyOfRange, iArrCopyOfRange.length + 2);
        iArrCopyOf[iArrCopyOf.length - 2] = iIndexOfWordStart2;
        iArrCopyOf[iArrCopyOf.length - 1] = (i - iIndexOfWordStart2) + 1;
        return iArrCopyOf;
    }

    private boolean isWordBoundary(int i) {
        return this.wordBoundaries.get(i);
    }
}
