package com.sun.org.apache.xerces.internal.impl.dv.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class HexBin {
    private static final int BASELENGTH = 128;
    private static final int LOOKUPLENGTH = 16;
    private static final byte[] hexNumberTable = new byte[128];
    private static final char[] lookUpHexAlphabet = new char[16];

    static {
        int i;
        int i2 = 0;
        for (int i3 = 0; i3 < 128; i3++) {
            hexNumberTable[i3] = -1;
        }
        for (int i4 = 57; i4 >= 48; i4--) {
            hexNumberTable[i4] = (byte) (i4 - 48);
        }
        for (int i5 = 70; i5 >= 65; i5--) {
            hexNumberTable[i5] = (byte) (i5 - 55);
        }
        for (int i6 = 102; i6 >= 97; i6--) {
            hexNumberTable[i6] = (byte) (i6 - 87);
        }
        while (true) {
            if (i2 >= 10) {
                break;
            }
            lookUpHexAlphabet[i2] = (char) (i2 + 48);
            i2++;
        }
        for (i = 10; i <= 15; i++) {
            lookUpHexAlphabet[i] = (char) (i + 55);
        }
    }

    public static byte[] decode(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int i = length / 2;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 * 2;
            char c = charArray[i3];
            byte b = c < 128 ? hexNumberTable[c] : (byte) -1;
            if (b == -1) {
                return null;
            }
            char c2 = charArray[i3 + 1];
            byte b2 = c2 < 128 ? hexNumberTable[c2] : (byte) -1;
            if (b2 == -1) {
                return null;
            }
            bArr[i2] = (byte) (b2 | (b << 4));
        }
        return bArr;
    }

    public static String encode(byte[] bArr) {
        if (bArr == 0) {
            return null;
        }
        int length = bArr.length;
        char[] cArr = new char[length * 2];
        for (int i = 0; i < length; i++) {
            int i2 = bArr[i];
            if (i2 < 0) {
                i2 += 256;
            }
            int i3 = i * 2;
            char[] cArr2 = lookUpHexAlphabet;
            cArr[i3] = cArr2[i2 >> 4];
            cArr[i3 + 1] = cArr2[i2 & 15];
        }
        return new String(cArr);
    }
}
