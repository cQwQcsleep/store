package com.shadow.okio.internal;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okio.Base64;
import com.shadow.okio.Buffer;
import com.shadow.okio.SegmentedByteString;
import com.shadow.okio._JvmPlatformKt;
import java.util.Arrays;

/* renamed from: com.shadow.okio.internal.-ByteString, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class ByteString {
    private static final char[] HEX_DIGIT_CHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x005c, code lost:
    
        return -1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final int codePointIndexToCharIndex(byte[] bArr, int i) {
        byte b;
        int i2;
        int length = bArr.length;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        loop0: while (i3 < length) {
            byte b2 = bArr[i3];
            if (b2 >= 0) {
                int i6 = i5 + 1;
                if (i5 == i) {
                    return i4;
                }
                if ((b2 != 10 && b2 != 13 && ((b2 >= 0 && b2 < 32) || (127 <= b2 && b2 < 160))) || b2 == 65533) {
                    return -1;
                }
                i4 += b2 < 65536 ? 1 : 2;
                i3++;
                while (true) {
                    i5 = i6;
                    if (i3 < length && (b = bArr[i3]) >= 0) {
                        i3++;
                        i6 = i5 + 1;
                        if (i5 != i) {
                            if ((b != 10 && b != 13 && ((b >= 0 && b < 32) || (127 <= b && b < 160))) || b == 65533) {
                                break loop0;
                            }
                            i4 += b < 65536 ? 1 : 2;
                        } else {
                            return i4;
                        }
                    } else {
                        break;
                    }
                }
            } else if ((b2 >> 5) == -2) {
                int i7 = i3 + 1;
                if (length <= i7) {
                    if (i5 == i) {
                        return i4;
                    }
                    return -1;
                }
                byte b3 = bArr[i7];
                if ((b3 & 192) != 128) {
                    if (i5 == i) {
                        return i4;
                    }
                    return -1;
                }
                int i8 = (b2 << 6) ^ (b3 ^ 3968);
                if (i8 < 128) {
                    if (i5 == i) {
                        return i4;
                    }
                    return -1;
                }
                int i9 = i5 + 1;
                if (i5 == i) {
                    return i4;
                }
                if ((i8 != 10 && i8 != 13 && ((i8 >= 0 && i8 < 32) || (127 <= i8 && i8 < 160))) || i8 == 65533) {
                    return -1;
                }
                i4 += i8 < 65536 ? 1 : 2;
                i3 += 2;
                i5 = i9;
            } else {
                if ((b2 >> 4) == -2) {
                    int i10 = i3 + 2;
                    if (length <= i10) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b4 = bArr[i3 + 1];
                    if ((b4 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b5 = bArr[i10];
                    if ((b5 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i11 = (b2 << 12) ^ ((b5 ^ (-123008)) ^ (b4 << 6));
                    if (i11 < 2048) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (55296 <= i11 && i11 < 57344) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if ((i11 != 10 && i11 != 13 && ((i11 >= 0 && i11 < 32) || (127 <= i11 && i11 < 160))) || i11 == 65533) {
                        return -1;
                    }
                    i4 += i11 < 65536 ? 1 : 2;
                    i3 += 3;
                } else {
                    if ((b2 >> 3) != -2) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i12 = i3 + 3;
                    if (length <= i12) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b6 = bArr[i3 + 1];
                    if ((b6 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b7 = bArr[i3 + 2];
                    if ((b7 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    byte b8 = bArr[i12];
                    if ((b8 & 192) != 128) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    int i13 = (b2 << 18) ^ (((b8 ^ 3678080) ^ (b7 << 6)) ^ (b6 << 12));
                    if (i13 > 1114111) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (55296 <= i13 && i13 < 57344) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    if (i13 < 65536) {
                        if (i5 == i) {
                            return i4;
                        }
                        return -1;
                    }
                    i2 = i5 + 1;
                    if (i5 == i) {
                        return i4;
                    }
                    if ((i13 != 10 && i13 != 13 && ((i13 >= 0 && i13 < 32) || (127 <= i13 && i13 < 160))) || i13 == 65533) {
                        return -1;
                    }
                    i4 += i13 < 65536 ? 1 : 2;
                    i3 += 4;
                }
                i5 = i2;
            }
        }
        return i4;
    }

    public static final String commonBase64(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        return Base64.encodeBase64$default(byteString.getData$okio(), null, 1, null);
    }

    public static final String commonBase64Url(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        return Base64.encodeBase64(byteString.getData$okio(), Base64.getBASE64_URL_SAFE());
    }

    public static final int commonCompareTo(com.shadow.okio.ByteString byteString, com.shadow.okio.ByteString byteString2) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(byteString2, "other");
        int size = byteString.size();
        int size2 = byteString2.size();
        int iMin = Math.min(size, size2);
        for (int i = 0; i < iMin; i++) {
            int i2 = byteString.getByte(i) & 255;
            int i3 = byteString2.getByte(i) & 255;
            if (i2 != i3) {
                return i2 < i3 ? -1 : 1;
            }
        }
        if (size == size2) {
            return 0;
        }
        return size < size2 ? -1 : 1;
    }

    public static final void commonCopyInto(com.shadow.okio.ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "target");
        ArraysKt.b(byteString.getData$okio(), i2, bArr, i, i3 + i);
    }

    public static final com.shadow.okio.ByteString commonDecodeBase64(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        byte[] bArrDecodeBase64ToArray = Base64.decodeBase64ToArray(str);
        if (bArrDecodeBase64ToArray != null) {
            return new com.shadow.okio.ByteString(bArrDecodeBase64ToArray);
        }
        return null;
    }

    public static final com.shadow.okio.ByteString commonDecodeHex(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: ".concat(str).toString());
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (decodeHexDigit(str.charAt(i2 + 1)) + (decodeHexDigit(str.charAt(i2)) << 4));
        }
        return new com.shadow.okio.ByteString(bArr);
    }

    public static final com.shadow.okio.ByteString commonEncodeUtf8(String str) {
        CloseableKt.checkNotNullParameter(str, "<this>");
        com.shadow.okio.ByteString byteString = new com.shadow.okio.ByteString(_JvmPlatformKt.asUtf8ToByteArray(str));
        byteString.setUtf8$okio(str);
        return byteString;
    }

    public static final boolean commonEndsWith(com.shadow.okio.ByteString byteString, com.shadow.okio.ByteString byteString2) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(byteString2, "suffix");
        return byteString.rangeEquals(byteString.size() - byteString2.size(), byteString2, 0, byteString2.size());
    }

    public static final boolean commonEquals(com.shadow.okio.ByteString byteString, Object obj) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        if (obj == byteString) {
            return true;
        }
        if (obj instanceof com.shadow.okio.ByteString) {
            com.shadow.okio.ByteString byteString2 = (com.shadow.okio.ByteString) obj;
            if (byteString2.size() == byteString.getData$okio().length && byteString2.rangeEquals(0, byteString.getData$okio(), 0, byteString.getData$okio().length)) {
                return true;
            }
        }
        return false;
    }

    public static final byte commonGetByte(com.shadow.okio.ByteString byteString, int i) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        return byteString.getData$okio()[i];
    }

    public static final int commonGetSize(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        return byteString.getData$okio().length;
    }

    public static final int commonHashCode(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        int hashCode$okio = byteString.getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int iHashCode = Arrays.hashCode(byteString.getData$okio());
        byteString.setHashCode$okio(iHashCode);
        return iHashCode;
    }

    public static final String commonHex(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        char[] cArr = new char[byteString.getData$okio().length * 2];
        int i = 0;
        for (byte b : byteString.getData$okio()) {
            int i2 = i + 1;
            cArr[i] = getHEX_DIGIT_CHARS()[(b >> 4) & 15];
            i += 2;
            cArr[i2] = getHEX_DIGIT_CHARS()[b & 15];
        }
        return new String(cArr);
    }

    public static final int commonIndexOf(com.shadow.okio.ByteString byteString, byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "other");
        int length = byteString.getData$okio().length - bArr.length;
        int iMax = Math.max(i, 0);
        if (iMax > length) {
            return -1;
        }
        while (!SegmentedByteString.arrayRangeEquals(byteString.getData$okio(), iMax, bArr, 0, bArr.length)) {
            if (iMax == length) {
                return -1;
            }
            iMax++;
        }
        return iMax;
    }

    public static final byte[] commonInternalArray(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        return byteString.getData$okio();
    }

    public static final int commonLastIndexOf(com.shadow.okio.ByteString byteString, com.shadow.okio.ByteString byteString2, int i) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(byteString2, "other");
        return byteString.lastIndexOf(byteString2.internalArray$okio(), i);
    }

    public static final com.shadow.okio.ByteString commonOf(byte[] bArr) {
        CloseableKt.checkNotNullParameter(bArr, "data");
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return new com.shadow.okio.ByteString(bArrCopyOf);
    }

    public static final boolean commonRangeEquals(com.shadow.okio.ByteString byteString, int i, com.shadow.okio.ByteString byteString2, int i2, int i3) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(byteString2, "other");
        return byteString2.rangeEquals(i2, byteString.getData$okio(), i, i3);
    }

    public static final boolean commonStartsWith(com.shadow.okio.ByteString byteString, com.shadow.okio.ByteString byteString2) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(byteString2, "prefix");
        return byteString.rangeEquals(0, byteString2, 0, byteString2.size());
    }

    public static final com.shadow.okio.ByteString commonSubstring(com.shadow.okio.ByteString byteString, int i, int i2) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(byteString, i2);
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (iResolveDefaultParameter <= byteString.getData$okio().length) {
            if (iResolveDefaultParameter - i >= 0) {
                return (i == 0 && iResolveDefaultParameter == byteString.getData$okio().length) ? byteString : new com.shadow.okio.ByteString(ArraysKt.d(byteString.getData$okio(), i, iResolveDefaultParameter));
            }
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
    }

    public static final com.shadow.okio.ByteString commonToAsciiLowercase(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        for (int i = 0; i < byteString.getData$okio().length; i++) {
            byte b = byteString.getData$okio()[i];
            if (b >= 65 && b <= 90) {
                byte[] data$okio = byteString.getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArrCopyOf[i2] = (byte) (b2 + 32);
                    }
                }
                return new com.shadow.okio.ByteString(bArrCopyOf);
            }
        }
        return byteString;
    }

    public static final com.shadow.okio.ByteString commonToAsciiUppercase(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        for (int i = 0; i < byteString.getData$okio().length; i++) {
            byte b = byteString.getData$okio()[i];
            if (b >= 97 && b <= 122) {
                byte[] data$okio = byteString.getData$okio();
                byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
                CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                bArrCopyOf[i] = (byte) (b - 32);
                for (int i2 = i + 1; i2 < bArrCopyOf.length; i2++) {
                    byte b2 = bArrCopyOf[i2];
                    if (b2 >= 97 && b2 <= 122) {
                        bArrCopyOf[i2] = (byte) (b2 - 32);
                    }
                }
                return new com.shadow.okio.ByteString(bArrCopyOf);
            }
        }
        return byteString;
    }

    public static final byte[] commonToByteArray(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        byte[] data$okio = byteString.getData$okio();
        byte[] bArrCopyOf = Arrays.copyOf(data$okio, data$okio.length);
        CloseableKt.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return bArrCopyOf;
    }

    public static final com.shadow.okio.ByteString commonToByteString(byte[] bArr, int i, int i2) {
        CloseableKt.checkNotNullParameter(bArr, "<this>");
        int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(bArr, i2);
        SegmentedByteString.checkOffsetAndCount(bArr.length, i, iResolveDefaultParameter);
        return new com.shadow.okio.ByteString(ArraysKt.d(bArr, i, iResolveDefaultParameter + i));
    }

    public static final String commonToString(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        if (byteString.getData$okio().length == 0) {
            return "[size=0]";
        }
        int iCodePointIndexToCharIndex = codePointIndexToCharIndex(byteString.getData$okio(), 64);
        if (iCodePointIndexToCharIndex != -1) {
            String strUtf8 = byteString.utf8();
            String strSubstring = strUtf8.substring(0, iCodePointIndexToCharIndex);
            CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
            String strO = StringsKt.o(StringsKt.o(StringsKt.o(strSubstring, "\\", "\\\\"), "\n", "\\n"), "\r", "\\r");
            if (iCodePointIndexToCharIndex >= strUtf8.length()) {
                return "[text=" + strO + ']';
            }
            return "[size=" + byteString.getData$okio().length + " text=" + strO + "…]";
        }
        if (byteString.getData$okio().length <= 64) {
            return "[hex=" + byteString.hex() + ']';
        }
        StringBuilder sb = new StringBuilder("[size=");
        sb.append(byteString.getData$okio().length);
        sb.append(" hex=");
        int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(byteString, 64);
        if (iResolveDefaultParameter > byteString.getData$okio().length) {
            throw new IllegalArgumentException(("endIndex > length(" + byteString.getData$okio().length + ')').toString());
        }
        if (iResolveDefaultParameter < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (iResolveDefaultParameter != byteString.getData$okio().length) {
            byteString = new com.shadow.okio.ByteString(ArraysKt.d(byteString.getData$okio(), 0, iResolveDefaultParameter));
        }
        sb.append(byteString.hex());
        sb.append("…]");
        return sb.toString();
    }

    public static final String commonUtf8(com.shadow.okio.ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        String utf8$okio = byteString.getUtf8$okio();
        if (utf8$okio != null) {
            return utf8$okio;
        }
        String utf8String = _JvmPlatformKt.toUtf8String(byteString.internalArray$okio());
        byteString.setUtf8$okio(utf8String);
        return utf8String;
    }

    public static final void commonWrite(com.shadow.okio.ByteString byteString, Buffer buffer, int i, int i2) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(buffer, "buffer");
        buffer.write(byteString.getData$okio(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int decodeHexDigit(char c) {
        if ('0' <= c && c < ':') {
            return c - '0';
        }
        if ('a' <= c && c < 'g') {
            return c - 'W';
        }
        if ('A' <= c && c < 'G') {
            return c - '7';
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    public static final char[] getHEX_DIGIT_CHARS() {
        return HEX_DIGIT_CHARS;
    }

    public static /* synthetic */ void getHEX_DIGIT_CHARS$annotations() {
    }

    public static final boolean commonEndsWith(com.shadow.okio.ByteString byteString, byte[] bArr) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "suffix");
        return byteString.rangeEquals(byteString.size() - bArr.length, bArr, 0, bArr.length);
    }

    public static final int commonLastIndexOf(com.shadow.okio.ByteString byteString, byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "other");
        for (int iMin = Math.min(SegmentedByteString.resolveDefaultParameter(byteString, i), byteString.getData$okio().length - bArr.length); -1 < iMin; iMin--) {
            if (SegmentedByteString.arrayRangeEquals(byteString.getData$okio(), iMin, bArr, 0, bArr.length)) {
                return iMin;
            }
        }
        return -1;
    }

    public static final boolean commonRangeEquals(com.shadow.okio.ByteString byteString, int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "other");
        return i >= 0 && i <= byteString.getData$okio().length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && SegmentedByteString.arrayRangeEquals(byteString.getData$okio(), i, bArr, i2, i3);
    }

    public static final boolean commonStartsWith(com.shadow.okio.ByteString byteString, byte[] bArr) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        CloseableKt.checkNotNullParameter(bArr, "prefix");
        return byteString.rangeEquals(0, bArr, 0, bArr.length);
    }
}
