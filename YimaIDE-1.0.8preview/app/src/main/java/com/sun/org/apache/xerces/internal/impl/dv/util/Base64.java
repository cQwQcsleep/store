package com.sun.org.apache.xerces.internal.impl.dv.util;

import com.sun.org.apache.bcel.internal.Const;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Base64 {
    private static final int BASELENGTH = 128;
    private static final int EIGHTBIT = 8;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static final char PAD = '=';
    private static final int SIGN = -128;
    private static final int SIXBIT = 6;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;
    private static final boolean fDebug = false;
    private static final byte[] base64Alphabet = new byte[128];
    private static final char[] lookUpBase64Alphabet = new char[64];

    static {
        int i = 0;
        for (int i2 = 0; i2 < 128; i2++) {
            base64Alphabet[i2] = -1;
        }
        for (int i3 = 90; i3 >= 65; i3--) {
            base64Alphabet[i3] = (byte) (i3 - 65);
        }
        for (int i4 = 122; i4 >= 97; i4--) {
            base64Alphabet[i4] = (byte) (i4 - 71);
        }
        for (int i5 = 57; i5 >= 48; i5--) {
            base64Alphabet[i5] = (byte) (i5 + 4);
        }
        byte[] bArr = base64Alphabet;
        bArr[43] = 62;
        bArr[47] = 63;
        for (int i6 = 0; i6 <= 25; i6++) {
            lookUpBase64Alphabet[i6] = (char) (i6 + 65);
        }
        int i7 = 26;
        int i8 = 0;
        while (i7 <= 51) {
            lookUpBase64Alphabet[i7] = (char) (i8 + 97);
            i7++;
            i8++;
        }
        int i9 = 52;
        while (i9 <= 61) {
            lookUpBase64Alphabet[i9] = (char) (i + 48);
            i9++;
            i++;
        }
        char[] cArr = lookUpBase64Alphabet;
        cArr[62] = '+';
        cArr[63] = '/';
    }

    public static byte[] decode(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int iRemoveWhiteSpace = removeWhiteSpace(charArray);
        if (iRemoveWhiteSpace % 4 != 0) {
            return null;
        }
        int i = iRemoveWhiteSpace / 4;
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i * 3];
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < i - 1) {
            int i5 = i3 + 1;
            char c = charArray[i3];
            if (isData(c)) {
                int i6 = i3 + 2;
                char c2 = charArray[i5];
                if (isData(c2)) {
                    int i7 = i3 + 3;
                    char c3 = charArray[i6];
                    if (isData(c3)) {
                        i3 += 4;
                        char c4 = charArray[i7];
                        if (isData(c4)) {
                            byte[] bArr2 = base64Alphabet;
                            byte b = bArr2[c];
                            byte b2 = bArr2[c2];
                            byte b3 = bArr2[c3];
                            byte b4 = bArr2[c4];
                            bArr[i4] = (byte) ((b << 2) | (b2 >> 4));
                            int i8 = i4 + 2;
                            bArr[i4 + 1] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
                            i4 += 3;
                            bArr[i8] = (byte) ((b3 << 6) | b4);
                            i2++;
                        }
                    }
                }
            }
            return null;
        }
        int i9 = i3 + 1;
        char c5 = charArray[i3];
        if (!isData(c5)) {
            return null;
        }
        int i10 = i3 + 2;
        char c6 = charArray[i9];
        if (!isData(c6)) {
            return null;
        }
        byte[] bArr3 = base64Alphabet;
        byte b5 = bArr3[c5];
        byte b6 = bArr3[c6];
        char c7 = charArray[i10];
        char c8 = charArray[i3 + 3];
        if (isData(c7) && isData(c8)) {
            byte b7 = bArr3[c7];
            byte b8 = bArr3[c8];
            bArr[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            bArr[i4 + 1] = (byte) (((b6 & 15) << 4) | ((b7 >> 2) & 15));
            bArr[i4 + 2] = (byte) (b8 | (b7 << 6));
            return bArr;
        }
        if (isPad(c7) && isPad(c8)) {
            if ((b6 & 15) != 0) {
                return null;
            }
            int i11 = i2 * 3;
            byte[] bArr4 = new byte[i11 + 1];
            System.arraycopy(bArr, 0, bArr4, 0, i11);
            bArr4[i4] = (byte) ((b5 << 2) | (b6 >> 4));
            return bArr4;
        }
        if (isPad(c7) || !isPad(c8)) {
            return null;
        }
        byte b9 = bArr3[c7];
        if ((b9 & 3) != 0) {
            return null;
        }
        int i12 = i2 * 3;
        byte[] bArr5 = new byte[i12 + 2];
        System.arraycopy(bArr, 0, bArr5, 0, i12);
        bArr5[i4] = (byte) ((b5 << 2) | (b6 >> 4));
        bArr5[i4 + 1] = (byte) (((b9 >> 2) & 15) | ((b6 & 15) << 4));
        return bArr5;
    }

    public static String encode(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i = length % 24;
        int i2 = length / 24;
        char[] cArr = new char[(i != 0 ? i2 + 1 : i2) * 4];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 0; i5 < i2; i5++) {
            byte b = bArr[i3];
            int i6 = i3 + 2;
            byte b2 = bArr[i3 + 1];
            i3 += 3;
            byte b3 = bArr[i6];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            int i7 = b & (-128);
            int i8 = b >> 2;
            if (i7 != 0) {
                i8 ^= 192;
            }
            byte b6 = (byte) i8;
            int i9 = b2 & (-128);
            int i10 = b2 >> 4;
            if (i9 != 0) {
                i10 ^= 240;
            }
            byte b7 = (byte) i10;
            byte b8 = (byte) ((b3 & (-128)) == 0 ? b3 >> 6 : (b3 >> 6) ^ Const.APPEND_FRAME);
            char[] cArr2 = lookUpBase64Alphabet;
            cArr[i4] = cArr2[b6];
            cArr[i4 + 1] = cArr2[b7 | (b5 << 4)];
            int i11 = i4 + 3;
            cArr[i4 + 2] = cArr2[(b4 << 2) | b8];
            i4 += 4;
            cArr[i11] = cArr2[b3 & 63];
        }
        if (i == 8) {
            byte b9 = bArr[i3];
            byte b10 = (byte) (b9 & 3);
            int i12 = b9 & (-128);
            int i13 = b9 >> 2;
            if (i12 != 0) {
                i13 ^= 192;
            }
            byte b11 = (byte) i13;
            char[] cArr3 = lookUpBase64Alphabet;
            cArr[i4] = cArr3[b11];
            cArr[i4 + 1] = cArr3[b10 << 4];
            cArr[i4 + 2] = PAD;
            cArr[i4 + 3] = PAD;
        } else if (i == 16) {
            byte b12 = bArr[i3];
            byte b13 = bArr[i3 + 1];
            byte b14 = (byte) (b13 & 15);
            byte b15 = (byte) (b12 & 3);
            int i14 = b12 & (-128);
            int i15 = b12 >> 2;
            if (i14 != 0) {
                i15 ^= 192;
            }
            byte b16 = (byte) i15;
            int i16 = b13 & (-128);
            int i17 = b13 >> 4;
            if (i16 != 0) {
                i17 ^= 240;
            }
            byte b17 = (byte) i17;
            char[] cArr4 = lookUpBase64Alphabet;
            cArr[i4] = cArr4[b16];
            cArr[i4 + 1] = cArr4[b17 | (b15 << 4)];
            cArr[i4 + 2] = cArr4[b14 << 2];
            cArr[i4 + 3] = PAD;
        }
        return new String(cArr);
    }

    public static boolean isBase64(char c) {
        return isWhiteSpace(c) || isPad(c) || isData(c);
    }

    public static boolean isData(char c) {
        return c < 128 && base64Alphabet[c] != -1;
    }

    public static boolean isPad(char c) {
        return c == '=';
    }

    public static boolean isWhiteSpace(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    public static int removeWhiteSpace(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!isWhiteSpace(cArr[i2])) {
                cArr[i] = cArr[i2];
                i++;
            }
        }
        return i;
    }
}
