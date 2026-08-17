package com.fasterxml.aalto.in;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ByteBasedPNameFactory {
    private static final ByteBasedPNameFactory sInstance = new ByteBasedPNameFactory();

    private ByteBasedPNameFactory() {
    }

    public static ByteBasedPNameFactory getInstance() {
        return sInstance;
    }

    public ByteBasedPName constructPName(int i, String str, int i2, int[] iArr, int i3) {
        if (i3 >= 4) {
            int[] iArr2 = new int[i3];
            for (int i4 = 0; i4 < i3; i4++) {
                iArr2[i4] = iArr[i4];
            }
            if (i2 < 0) {
                String strIntern = str.intern();
                return new PNameN(strIntern, null, strIntern, i, iArr2, i3);
            }
            return new PNameN(str, str.substring(0, i2).intern(), str.substring(i2 + 1).intern(), i, iArr2, i3);
        }
        if (i2 < 0) {
            String strIntern2 = str.intern();
            if (i3 == 3) {
                return new PName3(strIntern2, null, strIntern2, i, iArr);
            }
            return i3 == 2 ? new PName2(strIntern2, null, strIntern2, i, iArr[0], iArr[1]) : new PName1(strIntern2, null, strIntern2, i, iArr[0]);
        }
        String strSubstring = str.substring(0, i2);
        String strIntern3 = str.substring(i2 + 1).intern();
        String strIntern4 = strSubstring.intern();
        if (i3 == 3) {
            return new PName3(str, strIntern4, strIntern3, i, iArr);
        }
        return i3 == 2 ? new PName2(str, strIntern4, strIntern3, i, iArr[0], iArr[1]) : new PName1(str, strIntern4, strIntern3, i, iArr[0]);
    }
}
