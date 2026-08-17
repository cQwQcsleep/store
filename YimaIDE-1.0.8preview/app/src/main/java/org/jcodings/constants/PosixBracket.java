package org.jcodings.constants;

import org.jcodings.util.CaseInsensitiveBytesHash;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class PosixBracket {
    public static final byte[][] PBSNamesLower;
    public static final CaseInsensitiveBytesHash<Integer> PBSTableUpper;
    public static final int[] PBSValues;

    static {
        byte[][] bArr = {"alnum".getBytes(), "alpha".getBytes(), "blank".getBytes(), "cntrl".getBytes(), "digit".getBytes(), "graph".getBytes(), "lower".getBytes(), "print".getBytes(), "punct".getBytes(), "space".getBytes(), "upper".getBytes(), "xdigit".getBytes(), "ascii".getBytes(), "word".getBytes()};
        PBSNamesLower = bArr;
        PBSValues = new int[]{13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 12};
        PBSTableUpper = new CaseInsensitiveBytesHash<>(bArr.length + 5);
        int i = 0;
        while (true) {
            int[] iArr = PBSValues;
            if (i >= iArr.length) {
                return;
            }
            PBSTableUpper.put(PBSNamesLower[i], Integer.valueOf(iArr[i]));
            i++;
        }
    }
}
