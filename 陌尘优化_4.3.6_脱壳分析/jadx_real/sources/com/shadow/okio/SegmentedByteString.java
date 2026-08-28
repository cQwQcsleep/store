package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okio.Buffer;

/* renamed from: com.shadow.okio.-SegmentedByteString, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class SegmentedByteString {
    private static final Buffer.UnsafeCursor DEFAULT__new_UnsafeCursor = new Buffer.UnsafeCursor();
    private static final int DEFAULT__ByteString_size = -1234567890;

    public static final int and(byte b, int i) {
        return b & i;
    }

    public static final boolean arrayRangeEquals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        CloseableKt.checkNotNullParameter(bArr, "a");
        CloseableKt.checkNotNullParameter(bArr2, "b");
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    public static final int getDEFAULT__ByteString_size() {
        return DEFAULT__ByteString_size;
    }

    public static final Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return DEFAULT__new_UnsafeCursor;
    }

    public static /* synthetic */ void getDEFAULT__new_UnsafeCursor$annotations() {
    }

    public static final int leftRotate(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static final long minOf(long j, int i) {
        return Math.min(j, i);
    }

    public static final Buffer.UnsafeCursor resolveDefaultParameter(Buffer.UnsafeCursor unsafeCursor) {
        CloseableKt.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return unsafeCursor == DEFAULT__new_UnsafeCursor ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    public static final int reverseBytes(int i) {
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public static final long rightRotate(long j, int i) {
        return (j << (64 - i)) | (j >>> i);
    }

    public static final int shl(byte b, int i) {
        return b << i;
    }

    public static final int shr(byte b, int i) {
        return b >> i;
    }

    public static final String toHexString(byte b) {
        return new String(new char[]{com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(b >> 4) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[b & 15]});
    }

    public static final byte xor(byte b, byte b2) {
        return (byte) (b ^ b2);
    }

    public static final long and(byte b, long j) {
        return b & j;
    }

    public static final long minOf(int i, long j) {
        return Math.min(i, j);
    }

    public static final int resolveDefaultParameter(ByteString byteString, int i) {
        CloseableKt.checkNotNullParameter(byteString, "<this>");
        return i == DEFAULT__ByteString_size ? byteString.size() : i;
    }

    public static final long reverseBytes(long j) {
        return ((j & 255) << 56) | (((-72057594037927936L) & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public static final long and(int i, long j) {
        return i & j;
    }

    public static final int resolveDefaultParameter(byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(bArr, "<this>");
        return i == DEFAULT__ByteString_size ? bArr.length : i;
    }

    public static final short reverseBytes(short s) {
        return (short) (((s & 255) << 8) | ((65280 & s) >>> 8));
    }

    public static final String toHexString(int i) {
        int i2 = 0;
        if (i == 0) {
            return "0";
        }
        char[] cArr = {com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 28) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 24) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 20) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 16) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 12) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 8) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(i >> 4) & 15], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[i & 15]};
        while (i2 < 8 && cArr[i2] == '0') {
            i2++;
        }
        return StringsKt.c(cArr, i2, 8);
    }

    public static final String toHexString(long j) {
        if (j == 0) {
            return "0";
        }
        char[] cArr = {com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 60) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 56) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 52) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 48) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 44) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 40) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 36) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 32) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 28) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 24) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 20) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 16) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 12) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 8) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) ((j >> 4) & 15)], com.shadow.okio.internal.ByteString.getHEX_DIGIT_CHARS()[(int) (j & 15)]};
        int i = 0;
        while (i < 16 && cArr[i] == '0') {
            i++;
        }
        return StringsKt.c(cArr, i, 16);
    }
}
