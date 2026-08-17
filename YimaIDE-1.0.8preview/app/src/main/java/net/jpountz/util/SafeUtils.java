package net.jpountz.util;

import defpackage.w01;
import java.nio.ByteOrder;
import org.bouncycastle.crypto.hpke.HPKE;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public enum SafeUtils {
    ;

    public static void checkLength(int i) {
        if (i >= 0) {
            return;
        }
        w01.a("lengths must be >= 0");
    }

    public static void checkRange(byte[] bArr, int i, int i2) {
        checkLength(i2);
        if (i2 > 0) {
            checkRange(bArr, i);
            checkRange(bArr, (i + i2) - 1);
        }
    }

    public static byte readByte(byte[] bArr, int i) {
        return bArr[i];
    }

    public static int readInt(byte[] bArr, int i) {
        return Utils.NATIVE_BYTE_ORDER == ByteOrder.BIG_ENDIAN ? readIntBE(bArr, i) : readIntLE(bArr, i);
    }

    public static int readIntBE(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
    }

    public static int readIntLE(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public static int readShort(short[] sArr, int i) {
        return sArr[i] & HPKE.aead_EXPORT_ONLY;
    }

    public static int readShortLE(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    public static void writeByte(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) i2;
    }

    public static void writeInt(int[] iArr, int i, int i2) {
        iArr[i] = i2;
    }

    public static void writeShort(short[] sArr, int i, int i2) {
        sArr[i] = (short) i2;
    }

    public static void writeShortLE(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) i2;
        bArr[i + 1] = (byte) (i2 >>> 8);
    }

    public static void checkRange(byte[] bArr, int i) {
        if (i < 0 || i >= bArr.length) {
            y0e.a(i);
        }
    }

    public static int readInt(int[] iArr, int i) {
        return iArr[i];
    }
}
