package com.reandroid.utils;

import defpackage.bia;
import defpackage.sxf;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HexUtil {
    public static int decodeHex(String str, int i) {
        boolean z;
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        }
        int length = str.length() - 1;
        if (str.charAt(length) == 'L') {
            str = str.substring(0, length);
        }
        String strTrim0x = trim0x(str);
        int length2 = strTrim0x.length();
        int i2 = 0;
        for (int i3 = 0; i3 < length2; i3++) {
            int i4 = i2 << 4;
            int iDecodeHexChar = decodeHexChar(strTrim0x.charAt(i3));
            if (iDecodeHexChar == -1) {
                return i;
            }
            i2 = i4 | iDecodeHexChar;
        }
        return z ? -i2 : i2;
    }

    public static int decodeHexChar(char c) {
        if (c <= '9' && c >= '0') {
            return c - '0';
        }
        if (c <= 'f' && c >= 'a') {
            return c - 'W';
        }
        if (c > 'F' || c < 'A') {
            return -1;
        }
        return c - '7';
    }

    public static byte[] fromHexSting(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            bia.a("Odd hex string length: ", length);
            return null;
        }
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            int i2 = i + 1;
            bArr[i2 / 2] = (byte) (((decodeHexChar(str.charAt(i)) << 4) | decodeHexChar(str.charAt(i2))) & 255);
        }
        return bArr;
    }

    public static boolean isHexChar(char c) {
        if (c <= '9' && c >= '0') {
            return true;
        }
        if (c > 'f' || c < 'a') {
            return c <= 'F' && c >= 'A';
        }
        return true;
    }

    public static int parseHex(String str) {
        return (int) parseHexLong(str);
    }

    public static byte parseHexByte(String str) {
        String strSubstring;
        boolean z;
        if (str.charAt(0) == '-') {
            strSubstring = str.substring(1);
            z = true;
        } else {
            strSubstring = str;
            z = false;
        }
        if (strSubstring.charAt(0) == '+') {
            strSubstring = strSubstring.substring(1);
        }
        int length = strSubstring.length() - 1;
        if (strSubstring.charAt(length) == 't') {
            strSubstring = strSubstring.substring(0, length);
        }
        int i = Integer.parseInt(trim0x(strSubstring), 16);
        if ((i & 255) != i) {
            sxf.a("Invalid byte hex '", str, "'");
            return (byte) 0;
        }
        if (z) {
            i = -i;
        }
        return (byte) i;
    }

    public static int parseHexInteger(String str) {
        return (int) parseHexLong(str);
    }

    public static long parseHexLong(String str) {
        boolean z;
        if (str.charAt(0) == '-') {
            str = str.substring(1);
            z = true;
        } else {
            z = false;
        }
        if (str.charAt(0) == '+') {
            str = str.substring(1);
        }
        int length = str.length() - 1;
        if (str.charAt(length) == 'L') {
            str = str.substring(0, length);
        }
        String strTrim0x = trim0x(str);
        int length2 = strTrim0x.length();
        long j = 0;
        for (int i = 0; i < length2; i++) {
            long j2 = j << 4;
            int iDecodeHexChar = decodeHexChar(strTrim0x.charAt(i));
            if (iDecodeHexChar == -1) {
                sxf.a("Invalid hex char for string '", strTrim0x, "'");
                return 0L;
            }
            j = j2 | ((long) iDecodeHexChar);
        }
        return z ? -j : j;
    }

    public static short parseHexShort(String str) {
        String strSubstring;
        boolean z;
        if (str.charAt(0) == '-') {
            strSubstring = str.substring(1);
            z = true;
        } else {
            strSubstring = str;
            z = false;
        }
        if (strSubstring.charAt(0) == '+') {
            strSubstring = strSubstring.substring(1);
        }
        int length = strSubstring.length() - 1;
        char cCharAt = strSubstring.charAt(length);
        if (cCharAt == 'S' || cCharAt == 's') {
            strSubstring = strSubstring.substring(0, length);
        }
        int i = Integer.parseInt(trim0x(strSubstring), 16);
        if ((65535 & i) != i) {
            sxf.a("Invalid short hex '", str, "'");
            return (short) 0;
        }
        if (z) {
            i = -i;
        }
        return (short) i;
    }

    public static String toHex(String str, long j, int i) {
        String hexString = Long.toHexString(j);
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
        }
        int length = i - hexString.length();
        for (int i2 = 0; i2 < length; i2++) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }

    public static String toHex2(byte b) {
        return toHex(((long) b) & 255, 2);
    }

    public static String toHex4(short s) {
        return toHex(((long) s) & 65535, 4);
    }

    public static String toHex8(String str, int i) {
        return toHex(str, 4294967295L & ((long) i), 8);
    }

    public static char toHexChar(int i) {
        int i2;
        if (i < 0) {
            return (char) 0;
        }
        if (i < 10) {
            i2 = i + 48;
        } else {
            if (i > 16) {
                return (char) 0;
            }
            i2 = i + 87;
        }
        return (char) i2;
    }

    public static String toHexNoPrefix(int i, int i2) {
        return toHex((String) null, 4294967295L & ((long) i), i2);
    }

    public static String toHexNoPrefix8(int i) {
        return toHex((String) null, 4294967295L & ((long) i), 8);
    }

    public static String toHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            sb.append(toHexChar((b >> 4) & 15));
            sb.append(toHexChar(b & 15));
        }
        return sb.toString();
    }

    public static String toSignedHex(long j) {
        String str;
        if (j < 0) {
            j = -j;
            str = "-0x";
        } else {
            str = "0x";
        }
        return toHex(str, j, 1);
    }

    private static String trim0x(String str) {
        return (str == null || str.length() < 3 || str.charAt(0) != '0' || str.charAt(1) != 'x') ? str : str.substring(2);
    }

    public static String toHex2(String str, byte b) {
        return toHex(str, ((long) b) & 255, 2);
    }

    public static String toHex8(long j) {
        return toHex(j, 8);
    }

    public static String toHex8(int i) {
        return toHex(i, 8);
    }

    public static String toSignedHex(int i) {
        String str;
        if (i < 0) {
            i = -i;
            str = "-0x";
        } else {
            str = "0x";
        }
        return toHex(str, i, 1);
    }

    public static int decodeHexChar(byte b) {
        return decodeHexChar((char) (b & 255));
    }

    public static String toHex(long j, int i) {
        String hexString = Long.toHexString(j);
        StringBuilder sb = new StringBuilder("0x");
        int length = i - hexString.length();
        for (int i2 = 0; i2 < length; i2++) {
            sb.append('0');
        }
        sb.append(hexString);
        return sb.toString();
    }

    public static String toHex(String str, int i, int i2) {
        return toHex(str, 4294967295L & ((long) i), i2);
    }

    public static String toHex(int i, int i2) {
        return toHex(4294967295L & ((long) i), i2);
    }
}
