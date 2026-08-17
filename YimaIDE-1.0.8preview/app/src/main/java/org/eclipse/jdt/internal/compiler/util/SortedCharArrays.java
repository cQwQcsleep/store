package org.eclipse.jdt.internal.compiler.util;

import java.util.Comparator;
import org.eclipse.jdt.internal.compiler.util.SortedCharArrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class SortedCharArrays {
    public static final int BINARY_SEARCH_THRESHOLD = 16;
    public static final Comparator<char[]> CHAR_ARR_COMPARATOR = new Comparator() { // from class: vid
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return SortedCharArrays.compareCharArray((char[]) obj, (char[]) obj2);
        }
    };
    public static final Comparator<char[][]> CHAR_CHAR_ARR_COMPARATOR = new Comparator() { // from class: wid
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return SortedCharArrays.compareCharCharArray((char[][]) obj, (char[][]) obj2);
        }
    };

    public static int compareCharArray(char[] cArr, char[] cArr2) {
        if (cArr == cArr2) {
            return 0;
        }
        int length = cArr.length;
        int length2 = cArr2.length - length;
        if (length2 == 0) {
            for (int i = 0; i < length; i++) {
                length2 = cArr[i] - cArr2[i];
                if (length2 != 0) {
                    return length2;
                }
            }
        }
        return length2;
    }

    public static int compareCharCharArray(char[][] cArr, char[][] cArr2) {
        if (cArr == cArr2) {
            return 0;
        }
        int length = cArr.length;
        int length2 = cArr2.length - length;
        if (length2 == 0) {
            for (int i = 0; i < length; i++) {
                length2 = compareCharArray(cArr[i], cArr2[i]);
                if (length2 != 0) {
                    return length2;
                }
            }
        }
        return length2;
    }

    public static <T> T[] insertIntoArray(T[] tArr, T[] tArr2, T t, int i, int i2) {
        if (tArr != tArr2) {
            System.arraycopy(tArr, 0, tArr2, 0, i);
            System.arraycopy(tArr, i, tArr2, i + 1, i2 - i);
        } else if (i != i2) {
            System.arraycopy(tArr, i, tArr2, i + 1, i2 - i);
        }
        tArr2[i] = t;
        return tArr2;
    }
}
