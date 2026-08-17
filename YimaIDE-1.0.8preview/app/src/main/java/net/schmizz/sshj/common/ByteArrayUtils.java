package net.schmizz.sshj.common;

import defpackage.w01;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ByteArrayUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static byte[] encodeSensitiveStringToUtf8(char[] cArr) {
        CharsetEncoder charsetEncoderNewEncoder = Charset.forName("UTF-8").newEncoder();
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) (cArr.length * charsetEncoderNewEncoder.maxBytesPerChar()));
        charsetEncoderNewEncoder.encode(CharBuffer.wrap(cArr), byteBufferAllocate, true);
        Arrays.fill(cArr, ' ');
        int iPosition = byteBufferAllocate.position();
        byte[] bArr = new byte[iPosition];
        System.arraycopy(byteBufferAllocate.array(), 0, bArr, 0, iPosition);
        Arrays.fill(byteBufferAllocate.array(), (byte) 0);
        return bArr;
    }

    public static boolean equals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (bArr.length < i + i3 || bArr2.length < i2 + i3) {
            return false;
        }
        while (true) {
            int i4 = i3 - 1;
            if (i3 <= 0) {
                return true;
            }
            int i5 = i + 1;
            int i6 = i2 + 1;
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i = i5;
            i3 = i4;
            i2 = i6;
        }
    }

    public static byte[] parseHex(String str) {
        if (str == null) {
            w01.a("Hex string is null");
            return null;
        }
        if (str.length() % 2 != 0) {
            kg9.a("Hex string '", str, "' should have even length.");
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((parseHexDigit(str.charAt(i2)) << 4) + parseHexDigit(str.charAt(i2 + 1)));
        }
        return bArr;
    }

    private static int parseHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        throw new IllegalArgumentException("Digit '" + c + "' out of bounds [0-9a-fA-F]");
    }

    public static String printHex(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3];
            if (sb.length() > 0) {
                sb.append(' ');
            }
            char[] cArr = digits;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public static String toHex(byte[] bArr, int i, int i2) {
        StringBuilder sb = new StringBuilder();
        for (int i3 = 0; i3 < i2; i3++) {
            byte b = bArr[i + i3];
            char[] cArr = digits;
            sb.append(cArr[(b >> 4) & 15]);
            sb.append(cArr[b & 15]);
        }
        return sb.toString();
    }

    public static String toHex(byte[] bArr) {
        return toHex(bArr, 0, bArr.length);
    }
}
